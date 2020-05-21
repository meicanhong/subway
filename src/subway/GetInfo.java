package subway;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class GetInfo {
    // 存储线路上的站点信息
    public static List<SubwayLine> lines = new ArrayList<>();
    // 存储相邻站点
    public static List<Station> stations = new ArrayList<>();

    // 解析 subway.txt 获取地铁图信息
    public static void subwayMap() {
        String f = "subway.txt";
        try (
                // FileReader用于读取文件字符流
                FileReader r = new FileReader(f);
                // BufferedReader类从字符输入流中读取文本并缓冲字符
                BufferedReader br = new BufferedReader(r);
        ) {
            String line;
            // 分离每一条线路
            while((line = br.readLine()) != null) {
                // 分离线路名称
                String[] tokens = line.split("/",2);
                SubwayLine s1 =  new SubwayLine();
                // tokens[0]是线路名称
                s1.name = tokens[0];
                // 分离票价
                String[] tokens1 = tokens[1].split("%",2);
                s1.fare = Double.parseDouble(tokens1[0]);
                // 分离首末班时间
                String[] ftime = tokens1[1].split("%",2);
                s1.FirstTime = s1.simpleDateFormat.parse(ftime[0]);
                String[] ltime = ftime[1].split("-",2);
                s1.LastTime = s1.simpleDateFormat.parse(ltime[0]);
                // 分离各个站点
                String[] tokens2 = ltime[1].split(" ");
                // forEach循环，字符串s每次获取一个站点信息
                for(String s: tokens2) {
                    Station station = new Station();
                    // 判断该站点是否可换乘
                    boolean isTransfer = s.contains("#");
                    if(isTransfer) {
                        station.isTransferStation = true;
                        // 分离换乘站信息
                        String[] tokens3 = s.split("#");
                        station.name = tokens3[0];
                        // 解析该站点可换成的线路
                        for(int i = 1; i < tokens3.length; i++) {
                                if(tokens3[i].equals("1"))
                                    station.lines.add("1号线");
                                else if(tokens3[i].equals("2"))
                                    station.lines.add("2号线");
                                else if(tokens3[i].equals("4"))
                                    station.lines.add("4号线");
                                else if(tokens3[i].equals("5"))
                                    station.lines.add("5号线");
                                else if(tokens3[i].equals("6"))
                                    station.lines.add("6号线");
                                else if(tokens3[i].equals("7"))
                                    station.lines.add("7号线");
                                else if(tokens3[i].equals("8北"))
                                    station.lines.add("8号线北");
                                else if(tokens3[i].equals("8南"))
                                    station.lines.add("8号线南");
                                else if(tokens3[i].equals("9"))
                                    station.lines.add("9号线");
                                else if(tokens3[i].equals("10"))
                                    station.lines.add("10号线");
                                else if(tokens3[i].equals("13"))
                                    station.lines.add("13号线");
                                else if(tokens3[i].equals("14西"))
                                    station.lines.add("14号线西");
                                else if(tokens3[i].equals("14东"))
                                    station.lines.add("14号线东");
                                else if(tokens3[i].equals("15"))
                                    station.lines.add("15号线");
                                else if(tokens3[i].equals("16"))
                                    station.lines.add("16号线");
                                else if(tokens3[i].equals("八通"))
                                    station.lines.add("八通线");
                                else if(tokens3[i].equals("房山"))
                                    station.lines.add("房山线");
                                else if(tokens3[i].equals("昌平"))
                                    station.lines.add("昌平线");
                                else if(tokens3[i].equals("亦庄"))
                                    station.lines.add("亦庄线");
                                else if(tokens3[i].equals("燕房"))
                                    station.lines.add("燕房线");
                                else if(tokens3[i].equals("S1"))
                                    station.lines.add("S1线");
                                else if(tokens3[i].equals("西郊"))
                                    station.lines.add("西郊线");
                                else if(tokens3[i].equals("首都机场"))
                                    station.lines.add("首都机场线");
                            }
                        }
                        else {
                            if(s.contains("!")) {
                                continue;
                            }
                            station.isTransferStation = false;
                            station.name = s;
                        }
                    // 该站点所在线路
                    station.line = tokens[0];
                    // 添加途径站点
                    stations.add(station);
                    // 为该线路添加站点
                    s1.stations.add(station);
                    }
                lines.add(s1);
                }

            } catch (IOException | ParseException ex) {
            ex.printStackTrace();
        }
    }
    /*
    public static String getLineName(Station station) {
        for(SubwayLine s : lines) {
            for(Station st : s.stations) {
                if(st.name.equals(station.name)) {
                    return st.line;
                }
            }
        }
        return "";
    }
    */
}
