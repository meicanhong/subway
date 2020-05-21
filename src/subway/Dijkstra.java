package subway;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class Dijkstra {
    private static HashMap<Station, Result> shortestPath = new HashMap<>();
    private static List<Station> visitedStations = new ArrayList<>();

    public static Result calculate(Station startStation, Station endStation) {
        if (!visitedStations.contains(startStation)) {
            visitedStations.add(startStation);
        }
        if (startStation.equals(endStation)) {
            Result result = new Result();
            result.setDistance(0);
            result.setStartStation(startStation);
            result.setEndStation(startStation);
            return shortestPath.put(startStation, result);
        }
        if (shortestPath.isEmpty()) {
            List<Station> linkStations = getLinkStations(startStation);
            for (Station s : linkStations) {
                Result result = new Result();
                result.setStartStation(startStation);
                result.setEndStation(s);
                result.setDistance(1);
                result.getPassStations().add(s);
                shortestPath.put(s, result);
            }
        }
        Station nextStation = getNextStation();
        if (nextStation == null) {
            System.out.println("这种情况绝对不可能出现！");
        }
        if (nextStation.equals(endStation)) {
            return shortestPath.get(nextStation);
        }
        List<Station> nextStationLinkStations = getLinkStations(nextStation);
        for (Station linkStation : nextStationLinkStations) {
            if (visitedStations.contains(linkStation)) {
                continue;
            }
            int distance = shortestPath.get(nextStation).getDistance() + 1;
            List<Station> nextStationPassStations = shortestPath.get(nextStation).getPassStations();
            Result linkStationResult = shortestPath.get(linkStation);
            if (linkStationResult != null) {
                if (linkStationResult.getDistance() > distance) {
                    linkStationResult.setDistance(distance);
                    linkStationResult.getPassStations().clear();
                    linkStationResult.getPassStations().addAll(nextStationPassStations);
                    linkStationResult.getPassStations().add(linkStation);
                }
            } else {
                linkStationResult = new Result();
                linkStationResult.setDistance(distance);
                linkStationResult.setStartStation(startStation);
                linkStationResult.setEndStation(linkStation);
                linkStationResult.getPassStations().addAll(nextStationPassStations);
                linkStationResult.getPassStations().add(linkStation);
            }
            shortestPath.put(linkStation, linkStationResult);
        }
        visitedStations.add(nextStation);
        return calculate(startStation, endStation);
    }

    public static List<Station> getLinkStations(Station station) {
        List<Station> linkStations = new ArrayList<>();

        for (SubwayLine line : GetInfo.lines) {
            for (int i = 0; i < line.stations.size(); i++) {
                if (station.equals(line.stations.get(i))) {
                    if (i == 0)
                        linkStations.add(line.stations.get(i + 1));
                    else if (i == (line.stations.size() - 1))
                        linkStations.add(line.stations.get(i - 1));
                    else {
                        linkStations.add(line.stations.get(i - 1));
                        linkStations.add(line.stations.get(i + 1));
                    }
                }
            }
        }
        return linkStations;
    }

    public static Station getNextStation() {
        int min = 10000;
        Station nextStation = null;
        Set<Station> stations = shortestPath.keySet();
        for (Station s : stations) {
            if (visitedStations.contains(s))
                continue;
            Result r = shortestPath.get(s);
            if (r.getDistance() < min) {
                min = r.getDistance();
                nextStation = s;
            }
        }
        return nextStation;
    }

    public static void reset() {
        visitedStations.clear();
        shortestPath.clear();
    }
}
