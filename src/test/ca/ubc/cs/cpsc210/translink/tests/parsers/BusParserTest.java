package ca.ubc.cs.cpsc210.translink.tests.parsers;

import ca.ubc.cs.cpsc210.translink.model.RouteManager;
import ca.ubc.cs.cpsc210.translink.model.Stop;
import ca.ubc.cs.cpsc210.translink.model.StopManager;
import ca.ubc.cs.cpsc210.translink.parsers.BusParser;
import ca.ubc.cs.cpsc210.translink.parsers.RouteMapParser;
import ca.ubc.cs.cpsc210.translink.parsers.RouteParser;
import ca.ubc.cs.cpsc210.translink.parsers.StopParser;
import ca.ubc.cs.cpsc210.translink.parsers.exception.RouteDataMissingException;
import ca.ubc.cs.cpsc210.translink.parsers.exception.StopDataMissingException;
import ca.ubc.cs.cpsc210.translink.providers.FileDataProvider;
import org.json.JSONException;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BusParserTest {
    @Test
    public void testBusLocationsParserNormal() throws JSONException, IOException, StopDataMissingException, RouteDataMissingException {
        Stop s = StopManager.getInstance().getStopWithNumber(51479);
        s.clearBuses();
        String data = "";

        StopParser p = new StopParser("stops.json");
        p.parse();

        RouteParser rp = new RouteParser("allroutes.json");
        rp.parse();

        try {
            data = new FileDataProvider("buslocations.json").dataSourceToString();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Can't read the bus locations data");
        }

        BusParser.parseBuses(s, data);

        assertEquals(4, s.getBuses().size());
    }

    @Test
    public void testBusLocationRoute() throws JSONException {
        Stop stop = StopManager.getInstance().getStopWithNumber(51479);
        stop.addRoute(RouteManager.getInstance().getRouteWithNumber("004"));
        stop.addRoute(RouteManager.getInstance().getRouteWithNumber("014"));
        stop.clearBuses();
        String info = "";

        try{
            info = new FileDataProvider("busRouteException.json").dataSourceToString();
        }catch (IOException e){
            e.printStackTrace();
            throw new RuntimeException("Can't read the bus locations data");
        }

        BusParser.parseBuses(stop, info);

        assertEquals(3, stop.getBuses().size());


    }
}
