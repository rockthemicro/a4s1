package services;

import java.util.Iterator;
import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class MainController {

    @Autowired
    private ZborRepo zborRepo;

    @Autowired
    private RezervareRepo rezervareRepo;

    @RequestMapping("/route")
    public @ResponseBody String[] getOptimalRoute(@RequestParam(value="from") String from,
                                                  @RequestParam(value="to") String to,
                                                  @RequestParam(value="flights") Integer flights,
                                                  @RequestParam(value="day") Integer day) {

        if (flights < 1) {
            String[] result = new String[1];
            result[0] = "Can't calculate route for max flights smaller than 1";

            return result;
        }

        Iterable<Zbor> flightIterable = zborRepo.findAll();
        Iterator<Zbor> flightIterator = flightIterable.iterator();
        LinkedList<Zbor> candidati = new LinkedList<>();
        while (flightIterator.hasNext()) {
            Zbor zbor = flightIterator.next();
            if (zbor.getSeats() >= 1 && zbor.getDeparture_day() >= day) {
                candidati.add(zbor);
            }
        }


        LinkedList<Zbor> ruta = null;
        RouteCalculator rc = new RouteCalculator();
        ruta = rc.calculate(from, to, flights, day, candidati);

        if (ruta == null) {
            String[] result = new String[1];
            result[0] = "We couldn't find a route between " + from + " and " + to;

            return result;
        }

        String[] result = new String[ruta.size() + 1];
        result[0] = "ID Source Destination Duration Day Hour";

        for (int i = 0; i < ruta.size(); i++) {
            result[i + 1] =
                    ruta.get(i).getId()
                    + " " + ruta.get(i).getSource()
                    + " " + ruta.get(i).getDst()
                    + " " + ruta.get(i).getDuration()
                    + " " + ruta.get(i).getDeparture_day()
                    + " " + ruta.get(i).getDeparture_hour();
        }

        return result;
    }

    @RequestMapping("/book")
    public @ResponseBody String bookTicket(@RequestParam(value="flightIDs") String[] flights) {
        boolean seatsAvailable = true;
        int i = 0;

        for (i = 0; i < flights.length; i++) {
            Zbor zbor = zborRepo.findById(Integer.parseInt(flights[i])).get();

            if (zbor.getSeats() == 0) {
                seatsAvailable = false;
            }
        }

        if (seatsAvailable) {
            StringBuffer flightIDs = new StringBuffer();

            for (i = 0; i < flights.length; i++) {
                Zbor f = zborRepo.findById(Integer.parseInt(flights[i])).get();
                f.setSeats(f.getSeats() - 1);
                zborRepo.save(f);
            }

            for (String flightID : flights) {
                flightIDs.append(flightID + " ");
            }

            Rezervare rezervare = new Rezervare();
            rezervare.setFlightIDs(flightIDs.substring(0, flightIDs.length() - 1));
            rezervareRepo.save(rezervare);

            return "Reservation number is " + rezervare.getId().toString();
        }

        return "Can't make the reservation";
    }

    @RequestMapping("/buy")
    public @ResponseBody String buyTicket(@RequestParam(value="reservationID") String reservationNumber,
                                          @RequestParam(value="cardInfo") String cardInfo) {

        Integer id = Integer.parseInt(reservationNumber);

        if (rezervareRepo.existsById(Integer.parseInt(reservationNumber)) == false) {
            return "Non existent reservation";
        }

        Rezervare rezervare = rezervareRepo.findById(id).get();
        if (rezervare.getBought()) {
            return "Reservation was bought once before";
        }

        rezervare.setBought(true);
        rezervareRepo.save(rezervare);
        return "Reservation was made";
    }
}
