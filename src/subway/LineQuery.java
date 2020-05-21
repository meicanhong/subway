package subway;

import java.text.ParseException;
import java.util.Date;
import java.util.Scanner;

/*
线路查询：查询某条线路的票价，首班时间，末班时间，所有途径站点
 */
public class LineQuery {
    public void Qurey() throws ParseException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入您要查询的线路：");
        String sl = scanner.nextLine();

        GetInfo getInfo = new GetInfo();
        Station station = new Station();
        SubwayLine subwayLine = new SubwayLine();
        boolean hasfind = false;
        String passStation = sl;
        for (int i = 0; i < getInfo.lines.size(); i++) {
            subwayLine = getInfo.lines.get(i);
            if (sl.equals(subwayLine.name)) {
                hasfind = true;
                System.out.println(subwayLine.name + "的票价：" + subwayLine.fare + "元");
                System.out.println("首班时间：" + subwayLine.simpleDateFormat.format(subwayLine.FirstTime));
                System.out.println("末班时间：" + subwayLine.simpleDateFormat.format(subwayLine.LastTime));
                for (Station st : subwayLine.stations) {
                    passStation += st.name + "->";
                }
                /*for(int m=0;m<getInfo.stations.size();m++)
                {
                    station = getInfo.stations.get(m);
                    if(station.line==subwayLine.name)
                    {
                      System.out.println(station.name);
                    }
                }*/

            }
        }
        passStation = passStation.substring(0,passStation.length()-2);
        System.out.println(passStation);
        if (!hasfind) {
            System.out.println("线路查询失败！");
        }
    }
}
