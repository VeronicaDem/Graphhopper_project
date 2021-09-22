package logistics;

import logistics.Weighting.State;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Objects;

@Getter
@Setter
public class Station {
    double longitude;
    double latitude;
    State state;
    public Station(double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }
    public State calculateState(Map<String, Object > measures, MoscowMainStation moscowMainStation) {
        int countBads = 0;
        State res = State.GOOD;
        for(String key : moscowMainStation.getAverage().keySet()) {
            if(measures.containsKey(key)) {
                if (convertIntoDouble(measures.get(key)) > convertIntoDouble(moscowMainStation.getAverage().get(key))) {
                    countBads++;
                }
            }
        }
        int countMoscow = moscowMainStation.getAverage().keySet().size();
        if(countBads > countMoscow) {
            res = State.BAD;
        } else if(countBads >= countMoscow - 2) {
            res = State.MIDDLE;
        } else {
            res = State.GOOD;
        }
        return res;
    }


    private double convertIntoDouble(Object measure) {
        try {
            return (Integer) measure;
        } catch (Exception e) {
            return ((BigDecimal) measure).doubleValue();

        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Station station = (Station) o;
        return Double.compare(station.longitude, longitude) == 0 && Double.compare(station.latitude, latitude) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(longitude, latitude);
    }
}
