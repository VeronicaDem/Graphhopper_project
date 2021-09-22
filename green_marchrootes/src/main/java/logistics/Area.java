package logistics;

import logistics.Weighting.State;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;


@Getter
@Setter
public class Area {
    Station main_station;
    int name;
    Map<String, Boolean> isIncludedPoints;

    Area(Station station) {
        main_station = station;
        isIncludedPoints = new HashMap<>();
        name = hashCode();
    }

    @Override
    public String toString() {
        return "Area{" +
                "main_station=" + main_station +
                ", name=" + name +
                ", isIncludedPoints=" + isIncludedPoints +
                '}';
    }
    void calculateOnePart(double epsilon1, double epsilon2) {
        boolean isInnerFlag = true;
        while(isInnerFlag) {
            double latitude = main_station.getLatitude();
            double longitude = main_station.getLongitude();

            latitude += epsilon1;
            longitude += epsilon2;
            isInnerFlag = isInner(latitude, longitude);
            if(isInnerFlag) {
                isIncludedPoints.put(latitude + ";" + longitude, true);
            }
        }
    }
    void calculateArea() {
      calculateOnePart(1e-3, 1e-3);
      calculateOnePart(1e-3, 0);
      calculateOnePart(0, 1e-3);
      calculateOnePart(-1e-3, -1e-3);
      calculateOnePart(-1e-3, 0);
      calculateOnePart(0, -1e-3);

    }

    public boolean isInner(double latitude, double longitude) {
        Station nearestStation = Util.getNearestStation(longitude, latitude);
        System.out.println("longitude:" + nearestStation.getLongitude() +",latitude: " + nearestStation.getLatitude());
        System.out.println("main_station latitude == latitude: " + (nearestStation.getLatitude() == main_station.getLatitude()));
        System.out.println("main_station longitude = longitude:" + (nearestStation.getLongitude() == main_station.getLongitude()));
        return Util.getNearestStation(longitude, latitude).equals(main_station);
    }



}
