package subway;

import java.util.Scanner;

import static java.lang.System.exit;

public class GetShortestPath {
    public void getShortPath() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入起点站名称");
        String start = scanner.nextLine(); //起点站名称
        System.out.println("请输入终点站名称");
        String end = scanner.nextLine(); //终点站名称

        boolean isStart = false;
        boolean isEnd = false;
        for (Station s : GetInfo.stations) {
            if (start.equals(s.name))
                isStart = true;
            if (end.equals(s.name))
                isEnd = true;
            if (isStart && isEnd)
                break;
        }

        if (!isStart) {
            System.out.println("起点站不存在");
            exit(0);
        }

        if (!isEnd) {
            System.out.println("终点站不存在");
            exit(0);
        }

        Result result = Dijkstra.calculate(new Station(start), new Station(end));
        String str = "共经过" + result.getPassStations().size() + 1 + "站\n";
        str += "所在线路：" + result.getPassStations().get(0).line + " " + result.getStartStation().name + "->";
        String atLineName = result.getPassStations().get(0).line;
        Boolean isTransfer = false;

        for (int i = 0; i < result.getPassStations().size(); i++) {
            if (!atLineName.equals(result.getPassStations().get(i).line)) {
                isTransfer = true;
                atLineName = result.getPassStations().get(i).line;
            } else
                isTransfer = false;
            if (result.getPassStations().get(i).name.equals(""))
                str += "总站->";
            else if (isTransfer)
                str += "换乘到" + atLineName + "线路 " + result.getPassStations().get(i).name + "->";
            else
                str += result.getPassStations().get(i).name + "->";
        }
        str = str.substring(0, str.length() - 2);
        System.out.println(str);
        Dijkstra.reset();

    }

    public static String getLineName(Station station) {
        for (SubwayLine s : GetInfo.lines) {
            for (Station st : s.stations) {
                if (st.name.equals(station.name))
                    return st.line;
            }
        }
        return "";
    }
}
