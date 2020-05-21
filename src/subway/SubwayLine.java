package subway;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SubwayLine {
    //线路名称
    public String name;
    //线路票价
    public double fare;
    //首班时间
    Date FirstTime = new Date();
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
    //末班时间
    Date LastTime = new Date();
    //线上的站点
    public List<Station> stations = new ArrayList<>();

    public SubwayLine() throws ParseException {
    }
}
