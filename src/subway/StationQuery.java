package subway;

import java.text.ParseException;
import java.util.Scanner;

/*
（2）   站点信息查询：输入站点名称，显示站点所在线路信息，上一站点信息和下一站点信息
 */
public class StationQuery {
    public void Query() throws ParseException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入您要查询的站点：");
        String st = scanner.nextLine();

        GetInfo getInfo = new GetInfo();
        SubwayLine subwayLine = new SubwayLine();
        Station station = new Station();
        boolean hasfind = false;

        for(int i=0; i<getInfo.lines.size(); i++) {
            subwayLine = getInfo.lines.get(i);
            for(int j=0; j<subwayLine.stations.size(); j++) {
                station = subwayLine.stations.get(j);
                if( station.name.equals(st) ) {
                    hasfind = true;
                    System.out.println("站点："+st+"在线路"+subwayLine.name+"中");
                    // 获取当前站点的索引值
                    int indexOf = subwayLine.stations.indexOf(station);
                    // 判断是否存在上一站，若存在则打印上一站点名称
                    if(indexOf-1>-1) {
                        station = subwayLine.stations.get(indexOf-1);
                        System.out.println("上一站点为："+station.name);
                    }
                    // 判断是否存在下一站，若存在则打印上一站点名称
                    if(indexOf+1<subwayLine.stations.size()) {
                        station = subwayLine.stations.get(indexOf+1);
                        System.out.println("下一站点为："+station.name);
                    }
                }
            }
        }
        if(!hasfind) {
            System.out.println("对不起，找不到您要查询的站点。");
        }
    }
}
