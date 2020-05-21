package subway;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class Dijkstra {
    // 最短路径集合
    private static HashMap<Station, Result> shortestPath = new HashMap<>();
    // 已经分析过的站点集合
    private static List<Station> visitedStations = new ArrayList<>();

    public static Result calculate(Station startStation, Station endStation) {
        // 第一次使用calculate方法时将起点站添加到已分析集合里去
        if (!visitedStations.contains(startStation)) {
            visitedStations.add(startStation);
        }
        // 如果起点站和终点站相同，则返回经过0站
        if (startStation.equals(endStation)) {
            Result result = new Result();
            result.setDistance(0);
            result.setStartStation(startStation);
            result.setEndStation(startStation);
            return shortestPath.put(startStation, result);
        }
        // 第一次使用calculate方法时shortestPath集合才为空，此时将起点站的临近站点全加入到shortestPath集合
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
        // 获取下一个最佳站点
        Station nextStation = getNextStation();
        if (nextStation == null) {
            System.out.println("这种情况绝对不可能出现！");
        }
        // 最佳站点是终点站的时候，代表最短路径已经搞定了
        if (nextStation.equals(endStation)) {
            return shortestPath.get(nextStation);
        }
        // 获取最佳站点的临近站点，并将最佳站点加入到shortestPath集合里，更新最短路径
        List<Station> nextStationLinkStations = getLinkStations(nextStation);
        for (Station linkStation : nextStationLinkStations) {
            if (visitedStations.contains(linkStation)) {
                continue;
            }
            // 因为在原路径上加入最佳站点，所以距离+1
            int distance = shortestPath.get(nextStation).getDistance() + 1;
            // 获取最佳点经过的站点
            List<Station> nextStationPassStations = shortestPath.get(nextStation).getPassStations();
            Result linkStationResult = shortestPath.get(linkStation);
            // linkStationResult非空的意思是，现最短路径中有最佳点的邻接站点
            // 举个例子：现最短路径为：startStation->...->linkStation
            if (linkStationResult != null) {
                // 接上例子，startStation->...->linkStation这条路径长度为9，startStation->...->nextStation的长度为7
                // 则更新最短路径:startStation->...->nextStation->linkStation
                if (linkStationResult.getDistance() > distance) {
                    linkStationResult.setDistance(distance);
                    linkStationResult.getPassStations().clear();
                    linkStationResult.getPassStations().addAll(nextStationPassStations);
                    linkStationResult.getPassStations().add(linkStation);
                }
            }
            // 如果最近路径中无最佳站点的临近站点，则把最近站点添加到最短路径中。startStation->...->nextStation
            else {
                linkStationResult = new Result();
                linkStationResult.setDistance(distance);
                linkStationResult.setStartStation(startStation);
                linkStationResult.setEndStation(linkStation);
                linkStationResult.getPassStations().addAll(nextStationPassStations);
                linkStationResult.getPassStations().add(linkStation);
            }
            shortestPath.put(linkStation, linkStationResult);
        }
        // 将最佳点添加到已分析的集合中
        visitedStations.add(nextStation);
        return calculate(startStation, endStation);
    }

    // 获取所有的邻接站点
    public static List<Station> getLinkStations(Station station) {
        List<Station> linkStations = new ArrayList<>();

        for (SubwayLine line : GetInfo.lines) {
            for (int i = 0; i < line.stations.size(); i++) {
                if (station.equals(line.stations.get(i))) {
                    if (i == 0) //i=0的时候是总站，若想最短路径不要经过总站，改为i==1即可
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

    // 获取下一个最佳站点
    public static Station getNextStation() {
        int min = 10000;
        Station nextStation = null;
        Set<Station> stations = shortestPath.keySet();
        for (Station s : stations) {
            if (visitedStations.contains(s))
                continue;
            Result r = shortestPath.get(s);
            // 比较最短路径中没有被分析过的点，找出路径最短的站点
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
