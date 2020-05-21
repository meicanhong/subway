package subway;
import java.io.*;
import java.text.ParseException;
import java.util.Scanner;

import static java.lang.System.exit;

/*
地铁查询系统
问题说明：存储某个城市所有的地铁线路，并提供查询功能，具体功能如下
（1）   线路查询：查询某条线路的票价，首班时间，末班时间，所有途径站点
（2）   站点信息查询：输入站点名称，显示站点所在线路信息，上一站点信息和下一站点信息
（3）   线路查询，输入起始站点和到达站点给出最短乘车方案
（4）   保存所有信息到文件
（5）   从文件中读取所有信息
 */
public class Main {

    public static void main(String[] args) throws ParseException, IOException {
        GetInfo getInfo = new GetInfo();
        getInfo.subwayMap();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("-----------------地铁查询系统---------------------------\n" +
                    "-----------------请选择要查询的功能序号-------------------\n" +
                    "1、线路查询：查询某条线路的票价，首班时间，末班时间，所有途径站点\n" +
                    "2、站点信息查询：输入站点名称，显示站点所在线路信息，上一站点信息和下一站点信息\n" +
                    "3、线路查询，输入起始站点和到达站点给出最短乘车方案\n"+
                    "4、新增线路\n"+
                    "0、退出系统\n");
            System.out.print("请选择功能序号：");
            int type = scanner.nextInt();
            switch (type) {
                case 1:
                    LineQuery lineQuery = new LineQuery();
                    lineQuery.Qurey();
                    break;
                case 2:
                    StationQuery stationQuery = new StationQuery();
                    stationQuery.Query();
                    break;
                case 3:
                    GetShortestPath getShortestPath = new GetShortestPath();
                    getShortestPath.getShortPath();
                    break;
                case 4:
                    AddLine addLine = new AddLine();
                    addLine.login();
                    break;
                case 5:
                    test.getLinkStations(new Station());
                case 0:
                    exit(0);
                default:
                    System.out.println("输入序号错误，请重新输入。");
            }
            System.out.println(" ");
            System.out.println(" ");
        }

    }
}

