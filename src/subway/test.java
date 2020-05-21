package subway;

import java.util.ArrayList;
import java.util.List;

public class test {
    public static void getLinkStations(Station station) {
        List<Station> linkedStaions = new ArrayList<>();
        String name = "俸伯";
        for (SubwayLine line : GetInfo.lines) {
            for (int i = 1; i < line.stations.size(); i++) {
                // 这里取到所有站点的相邻站点，站点名称相同也取多次
                if (name.equals(line.stations.get(i).name)) {
                    System.out.println("i="+line.stations.size());
                    System.out.println(line.name+"  1：  "+line.stations.get(1).name);
                    System.out.println(line.name+" :20：  "+line.stations.get(20).name);
                    if (i == 1) {
                        linkedStaions.add(line.stations.get(i + 1));
                    }
                    else if (i == (line.stations.size() - 1)) {
                        linkedStaions.add(line.stations.get(i - 1));
                    }
                    else {
                        linkedStaions.add(line.stations.get(i + 1));
                        linkedStaions.add(line.stations.get(i - 1));
                    }
                }
            }
        }
        System.out.println(linkedStaions.get(0).name);
        System.out.println(linkedStaions.get(1).name);
        System.out.println("-------------");
    }
}
