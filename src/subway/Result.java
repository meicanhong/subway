package subway;
import java.util.ArrayList;
import java.util.List;

public class Result {

    private Station startStation;
    private Station endStation;
    private int distance;
    private List<Station> passStations = new ArrayList<>();

    public Station getStartStation() {
        return startStation;
    }
    public void setStartStation(Station startStation) {
        this.startStation = startStation;
    }
    public Station getEndStation() {
        return endStation;
    }
    public void setEndStation(Station endStation) {
        this.endStation = endStation;
    }
    public int getDistance() {
        return distance;
    }
    public void setDistance(int distance) {
        this.distance = distance;
    }
    public List<Station> getPassStations() {
        return passStations;
    }
    public void setPassStations(List<Station> passStations) {
        this.passStations = passStations;
    }

}