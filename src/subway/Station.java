package subway;

import java.util.ArrayList;
import java.util.List;

public class Station {
    //站点名称
    public String name = null;
    //站点所在线路
    public String line;
    //该站点是否支持换乘
    public Boolean isTransferStation;
    //可换成线路
    public List<String> lines = new ArrayList<>();
    //相邻站点
    public List<Station> AdjacentStation = new ArrayList<>();
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getLine() {
        return line;
    }
    public void setLine(String line) {
        this.line = line;
    }
    public Boolean getIsTransferStation() {
        return isTransferStation;
    }
    public void setIsTransferStation(Boolean isTransferStation) {
        this.isTransferStation = isTransferStation;
    }
    public List<String> getLines() {
        return lines;
    }
    public void setLines(List<String> lines) {
        this.lines = lines;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        else if(obj instanceof Station){
            Station s = (Station)obj;
            if(s.getName().equals(this.getName()))
                return true;
            else
                return false;
        }
        else
            return false;
    }

    @Override
    public int hashCode() {
        return this.getName().hashCode();
    }
    public Station() { }
    public Station(String s) {
        this.name = s;
    }

}
