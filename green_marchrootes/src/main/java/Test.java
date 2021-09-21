import com.graphhopper.GHRequest;
import com.graphhopper.GHResponse;
import com.graphhopper.GraphHopper;

import com.graphhopper.routing.QueryGraph;
import com.graphhopper.routing.util.EncodingManager;
import com.graphhopper.routing.weighting.TurnWeighting;
import com.graphhopper.storage.GraphBuilder;
import com.graphhopper.storage.GraphHopperStorage;
import com.graphhopper.storage.index.QueryResult;
import com.graphhopper.util.PointList;
import logistics.MyGraphHopper;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

public class Test {
    public static void main(String[] args) {
        EncodingManager encodingManager = new EncodingManager("car");

        //GraphHopperStorage graph = new GraphBuilder(encodingManager).setLocation("src/main/result").create();
        /*GraphHopper graphHopper = new MyGraphHopper()
                                                    .setOSMFile("src/main/resources/russia-latest.osm.pbf")
                                                    .forServer()
                                                    .setStoreOnFlush(true)
                                                    .setGraphHopperLocation("src/main/result")
                                                    .setEncodingManager(encodingManager)
                                                     //.setCHEnabled(false)
                                                    ;
        System.out.println(graphHopper);

        graphHopper.importOrLoad();

        GHRequest req = new GHRequest(55.753220, 37.622513, 45.040342, 39.017544).
                setWeighting("common weighting").
                setVehicle("car").
                setLocale(Locale.US);
        System.out.println(req);
        GHResponse rsp = graphHopper.route(req);
        PointList pointList = rsp.getBest().getPoints();
        System.out.println("точки:" + pointList);
             */
       /* GraphBuilder gb = new GraphBuilder(encodingManager)
                              .setLocation("src/main/result")
                              .setStore(true)

                ;
        GraphHopperStorage graph = gb.create();

        */
       /* OSMReader reader = new OSMReader(graph);
        reader.setFile(new File("src/main/resources/russia-latest.osm.pbf"));
        try {
            reader.readGraph();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        GraphHopper graphHopper = new MyGraphHopper()
                .setDataReaderFile("src/main/resources/RU-MOW.pbf")
                .forServer()
                .setStoreOnFlush(true)
                .setGraphHopperLocation("src/main/result")
                .setEncodingManager(encodingManager)
                .setCHEnabled(false)
                ;
        encodingManager.supports(String.valueOf(TurnWeighting.class));
        //graphHopper.setGraphHopperStorage(graph);
                //.setCHEnabled(false)

        System.out.println(graphHopper);
       // System.out.println(encodingManager.supports(TurnWeighting.class.getName()));
        GraphHopper hopper = graphHopper.importOrLoad();

        GHRequest req = new GHRequest(55.753220, 37.622513, 55.6707134, 37.48150251595267).
                setWeighting("common").
                setVehicle("car").
                setLocale(Locale.US);
        System.out.println(req);
        GHResponse rsp = hopper.route(req);
        System.out.println("points");
        PointList pointList = rsp.getBest().getPoints();
        System.out.println("точки:" + pointList);
        hopper.close();
    }
}
