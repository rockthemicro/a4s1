package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

@Controller
@RequestMapping
public class MainController {

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @RequestMapping("/adaugare_zbor")
    public @ResponseBody String adaugare_zbor(@RequestParam(value="source") String source,
                                 @RequestParam(value="dst") String dst,
                                 @RequestParam(value="departure_day") Integer departure_day,
                                 @RequestParam(value="departure_hour") Integer departure_hour,
                                 @RequestParam(value="duration") Integer duration,
                                 @RequestParam(value="seats") Integer seats,
                                 @RequestParam(value="id") Integer id) {

        Flight f = new Flight();
        f.setSource(source);
        f.setDst(dst);
        f.setDeparture_day(departure_day);
        f.setDeparture_hour(departure_hour);
        f.setDuration(duration);
        f.setSeats(seats);
        f.setId(id);

        if (departure_day > 365) {
            return "Departure day is later than 31st of December";
        }

        int days = duration / 24;
        int hours = duration % 24;

        int arrival_day = departure_day;
        if (days > 0) {
            arrival_day += days;
        }

        if (hours + departure_hour > 24) {
            arrival_day += 1;
        }

        if (arrival_day > 365) {
            return "Arriving later than 31st of December";
        }

        if (flightRepository.existsById(id)) {
            return "id " + id + " exists";
        } else {
            flightRepository.save(f);
            return id + " saved";
        }

    }

    @RequestMapping("/anulare_zbor")
    public @ResponseBody String anulare_zbor(@RequestParam(value="id") Integer id) {
        if (flightRepository.existsById(id)) {

            flightRepository.deleteById(id);
            return id + " deleted";

        } else {

            return id + " doesn't exist";
        }
    }

    @RequestMapping("/get_optimal_route")
    public @ResponseBody String[] getOptimalRoute(@RequestParam(value="src") String source,
                                                  @RequestParam(value="dst") String destination,
                                                  @RequestParam(value="max") Integer maxFlights,
                                                  @RequestParam(value="day") Integer departureDay) {


        Iterable<Flight> flightIterable = flightRepository.findAll();
        Iterator<Flight> flightIterator = flightIterable.iterator();
        ArrayList<Flight> possibleSources = new ArrayList<>();
        ArrayList<Flight> intermediaryFlights = new ArrayList<>();


        while (flightIterator.hasNext()) {
            Flight f = flightIterator.next();

            if (f.getSeats() > 0) {

                if (f.getDeparture_day() == departureDay
                        && f.getSource().equalsIgnoreCase(source)) {

                    possibleSources.add(f);
                } else if (f.getDeparture_day() >= departureDay
                        && f.getSource().equalsIgnoreCase(source) == false
                        && f.getDst().equalsIgnoreCase(source) == false) {

                    intermediaryFlights.add(f);
                }
            }
        }

        int best_cost = Integer.MAX_VALUE;
        ArrayList<Flight> best_cost_route = null;
        for (Flight possibleSource : possibleSources) {
            PathAndCost tmp = null;
            try {
                tmp = Dijkstra.getOptimalRoute(source, destination, maxFlights, departureDay,
                        possibleSource, intermediaryFlights);


                if (tmp != null && tmp.cost < best_cost) {
                    best_cost = tmp.cost;
                    best_cost_route = tmp.path;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        if (best_cost_route == null)
            return new String[] { "Non-existent route" };

        String[] result = new String[best_cost_route.size() + 1];
        result[0] = "Duration of best route: " + best_cost;
        ArrayList<Flight> path = best_cost_route;
        for (int i = 0; i < best_cost_route.size(); i++) {
            result[i + 1] = "ID=" + path.get(i).getId() + ", From=" + path.get(i).getSource()
                    + ", To=" + path.get(i).getDst() + ", Duration=" + path.get(i).getDuration()
                    + ", Departure_day=" + path.get(i).getDeparture_day()
                    + ", Departure_hour=" + path.get(i).getDeparture_hour();
        }

        return result;
    }

    @RequestMapping("/book_ticket")
    public @ResponseBody String bookTicket(@RequestParam(value="ids") String[] flightIDs) {
        boolean successful = true;

        for (int i = 0; i < flightIDs.length; i++) {
            Flight f = flightRepository.findById(Integer.parseInt(flightIDs[i])).get();

            if (f.getSeats() == 0) {
                successful = false;
                for (int j = 0; j < i; j ++) {
                    Flight tmp = flightRepository.findById(Integer.parseInt(flightIDs[j])).get();
                    tmp.setSeats(tmp.getSeats() + 1);
                    flightRepository.save(tmp);
                }

                break;

            } else {
                f.setSeats(f.getSeats() - 1);
                flightRepository.save(f);
            }
        }

        if (successful) {
            StringBuilder ticketFlightIDs = new StringBuilder();
            for (String flightID : flightIDs) {
                ticketFlightIDs.append(flightID + ":");
            }

            Ticket t = new Ticket();
            t.setFlightIDs(ticketFlightIDs.substring(0, ticketFlightIDs.length() - 1));
            ticketRepository.save(t);

            return t.getId().toString();
        }

        return "Could not be booked";
    }

    @RequestMapping("/buy_ticket")
    public @ResponseBody String buyTicket(@RequestParam(value="id") String reservationID,
                                          @RequestParam(value="card") String creditCardInformation) {

        if (!ticketRepository.existsById(Integer.parseInt(reservationID))) {
            return "Reservation doesn't exist!";
        }

        Ticket t = ticketRepository.findById(Integer.parseInt(reservationID)).get();
        if (t.getBought()) {
            return "Ticket already bought once!";
        }

        t.setBought(true);
        ticketRepository.save(t);
        return "Ticked bought successfully! :)";
    }
}
