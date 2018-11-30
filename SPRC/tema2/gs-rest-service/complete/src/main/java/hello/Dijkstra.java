package hello;

import java.util.*;

public class Dijkstra {

    public static PathAndCost getOptimalRoute(String source, String destination, Integer maxFlights,
                                                    Integer departureDay, Flight firstFlight,
                                                    ArrayList<Flight> intermediaryFlights) throws Exception {


        HashMap<String, String> parent = new HashMap<>();
        parent.put(source, "");

        HashMap<String, Integer> distanceToSource = new HashMap<>();
        distanceToSource.put(source, 0);

        HashMap<String, Flight> lastFlight = new HashMap<>();
        lastFlight.put(source, null);

        ArrayList<State> states = new ArrayList<>();
        states.add(applyFlight(
                new State(
                    firstFlight.getSource(),
                    firstFlight.getDeparture_day(),
                    firstFlight.getDeparture_hour()
                ),
                firstFlight)
        );

        parent.put(states.get(0).city, source);
        distanceToSource.put(states.get(0).city, firstFlight.getDuration());
        lastFlight.put(states.get(0).city, firstFlight);

        for (int i = 0; i < maxFlights; i++) {
            ArrayList<State> newStates = new ArrayList<>();

            while (states.isEmpty() == false) {
                State s = states.remove(0);
                ArrayList<Flight> flightsFromHere = getFlightsFromState(s, intermediaryFlights);

                int currentCost = distanceToSource.get(s.city);
                for (Flight f : flightsFromHere) {
                    int idleCost = getIdleCost(s, f);
                    int newCost = currentCost + idleCost + f.getDuration();

                    if (distanceToSource.containsKey(f.getDst()) == false) {
                        parent.put(f.getDst(), f.getSource());
                        distanceToSource.put(f.getDst(), newCost);
                        lastFlight.put(f.getDst(), f);

                        newStates.add(applyFlight(s, f));
                    } else if (distanceToSource.get(f.getDst()) > newCost) {
                        ArrayList<State> tmpStates = new ArrayList<>();
                        for (State tmpState : newStates) {
                            if (tmpState.city.equalsIgnoreCase(f.getDst()) == false) {
                                tmpStates.add(tmpState);
                            }
                        }
                        newStates = tmpStates;

                        parent.put(f.getDst(), f.getSource());
                        distanceToSource.put(f.getDst(), newCost);
                        lastFlight.put(f.getDst(), f);

                        newStates.add(applyFlight(s, f));
                    }
                }
            }

            states = newStates;
        }

        if (parent.containsKey(destination) == false) {
            return null;
        }

        ArrayList<Flight> result = new ArrayList<>();

        String current = destination;
        while (current.equalsIgnoreCase(source) == false) {
            result.add(lastFlight.get(current));
            current = parent.get(current);
        }

        Collections.reverse(result);
        return new PathAndCost(distanceToSource.get(destination), result);
    }

    private static ArrayList<Flight> getFlightsFromState(State s, ArrayList<Flight> intermediaryFlights) {
        ArrayList<Flight> result = new ArrayList<>();

        for (Flight f : intermediaryFlights) {
            if (f.getSeats() > 0 && f.getSource().equalsIgnoreCase(s.city)
                    && (f.getDeparture_day() > s.day
                        || (f.getDeparture_day() == s.day
                         && f.getDeparture_hour() >= s.hour))) {

                result.add(f);
            }
        }

        return result;
    }




    public static int getIdleCost(State start, Flight flight) throws Exception {
        if (start.day > flight.getDeparture_day()
                || (start.day == flight.getDeparture_day() && start.hour > flight.getDeparture_hour())) {

            throw new Exception("getIdleCost");
        }

        int cost = 0;

        if (start.day < flight.getDeparture_day()) {
            cost += (24 - start.hour);
            cost += (flight.getDeparture_day() - start.day - 1) * 24;
            cost += flight.getDeparture_hour();
        } else {
            cost += flight.getDeparture_hour() - start.hour;
        }

        return cost;
    }

    public static State applyFlight(State start, Flight flight) throws Exception {
        if (start.city.equalsIgnoreCase(flight.getSource()) == false) {
            throw new Exception("applyFlight");
        }

        int duration = start.hour + getIdleCost(start, flight);

        State end = new State(flight.getDst(), 0, 0);
        if (flight.getDuration() + duration >= 24) {
            end.day = start.day + ((flight.getDuration() + duration) / 24);
            end.hour = (flight.getDuration() + duration) % 24;

        } else {
            end.day = start.day;
            end.hour = flight.getDuration() + duration;
        }

        return end;
    }
}

class PathAndCost {
    Integer cost;
    ArrayList<Flight> path;

    public PathAndCost(Integer cost, ArrayList<Flight> path) {
        this.cost = cost;
        this.path = path;
    }
}

class State {
    String city;
    Integer day;
    Integer hour;

    public State(String city, Integer day, Integer hour) {
        this.city = city;
        this.day = day;
        this.hour = hour;
    }
}