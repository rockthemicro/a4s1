package services;


import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;

public class RouteCalculator {
    private Integer global_cost = -1;
    private LinkedList<Zbor> global_path = null;

    public LinkedList<Zbor> calculate(String from, String to, Integer flights, Integer day, LinkedList<Zbor> candidates) {
        for (Zbor zbor : candidates) {
            if (zbor.getDeparture_day() == day && zbor.getSource().equalsIgnoreCase(from)) {
                calculateRouteStartingWith(zbor, from, to, flights, day, candidates);
            }
        }

        return global_path;
    }

    private void calculateRouteStartingWith(Zbor zbor, String from, String to, Integer flights, Integer day,
                                            LinkedList<Zbor> candidates) {

        HashMap<String, String> previousCity = new HashMap<>();
        HashMap<String, Integer> cost = new HashMap<>();
        HashMap<String, Zbor> previousFlight = new HashMap<>();

        previousCity.put(from, null);
        cost.put(from, 0);

        TimeSpaceMoment first = new TimeSpaceMoment(zbor);
        previousCity.put(zbor.getDst(), from);
        cost.put(zbor.getDst(), zbor.getDuration());
        previousFlight.put(zbor.getDst(), zbor);

        LinkedList<TimeSpaceMoment> reachable = new LinkedList<>();
        reachable.add(first);
        Integer iterations = 0;

        while (++iterations < flights && reachable.size() > 0) {
            LinkedList<TimeSpaceMoment> nextReachable = new LinkedList<>();

            for (TimeSpaceMoment moment : reachable) {
                for (Zbor urmZbor : candidates) {
                    Integer tmpCost = moment.getCostOfFlight(urmZbor);
                    if (tmpCost != -1) {

                        if (cost.containsKey(urmZbor.getDst()) == false) {
                            previousCity.put(urmZbor.getDst(), moment.getLocation());
                            cost.put(urmZbor.getDst(), cost.get(moment.getLocation()) + tmpCost);
                            previousFlight.put(urmZbor.getDst(), urmZbor);

                            nextReachable.add(new TimeSpaceMoment(urmZbor));

                        } else if (tmpCost + cost.get(moment.getLocation()) < cost.get(urmZbor.getDst())) {
                            previousCity.put(urmZbor.getDst(), moment.getLocation());
                            cost.put(urmZbor.getDst(), cost.get(moment.getLocation()) + tmpCost);
                            previousFlight.put(urmZbor.getDst(), urmZbor);

                            for (int i = 0; i < nextReachable.size(); i++) {
                                if (nextReachable.get(i).getLocation().equalsIgnoreCase(urmZbor.getDst())) {
                                    nextReachable.remove(i);
                                    break;
                                }
                            }

                            nextReachable.add(new TimeSpaceMoment(urmZbor));
                        }
                    }
                }
            }

            reachable = nextReachable;
        }

        if (cost.containsKey(to)) {
            if (cost.get(to) < global_cost || global_cost == -1) {
                String end = to;
                LinkedList<Zbor> newPath = new LinkedList<>();
                while (end.equalsIgnoreCase(from) == false) {
                    newPath.add(previousFlight.get(end));
                    end = previousCity.get(end);
                }
                Collections.reverse(newPath);

                global_cost = cost.get(to);
                global_path = newPath;
            }
        }
    }
}
