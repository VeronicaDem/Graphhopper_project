package logistics;

import logistics.Weighting.State;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;
@Getter
@Setter
public class MoscowMainStation extends Station{
    private Map<String, Object> average;
    public MoscowMainStation(double longitude, double latitude) {
        super(longitude, latitude);
        this.average = new HashMap<>();
        this.average = calculateAverage();
    }

    private Map<String, Object> calculateAverage() {
        return Util.getMeasurements(this.latitude, this.longitude);
    }


}
