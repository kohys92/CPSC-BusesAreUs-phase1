package ca.ubc.cs.cpsc210.translink.tests.model;

import ca.ubc.cs.cpsc210.translink.model.Stop;
import ca.ubc.cs.cpsc210.translink.model.StopManager;
import ca.ubc.cs.cpsc210.translink.model.exception.StopException;
import ca.ubc.cs.cpsc210.translink.util.LatLon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


/**
 * Test the StopManager
 */
public class StopManagerTest {

    @BeforeEach
    public void setup() {
        StopManager.getInstance().clearStops();
    }

    @Test
    public void testBasic() {
        Stop s9999 = new Stop(9999, "My house", new LatLon(-49.2, 123.2));
        Stop r = StopManager.getInstance().getStopWithNumber(9999);
        assertEquals(s9999, r);
    }

    @Test
    public void testSetSelected(){
        Stop s7777 = new Stop(7777, "My house", new LatLon(-49.2,123.2));
        Stop s8888 = new Stop(8888, "My school", new LatLon(-47.2,134.6));

        try{
            Stop stop = StopManager.getInstance().getStopWithNumber(7777);
            StopManager.getInstance().setSelected(s8888);
            fail("StopException should have not been thrown");
        }
        catch (StopException e){
            //expected
        }
        try{
            Stop stop = StopManager.getInstance().getStopWithNumber(7777);
            StopManager.getInstance().setSelected(s7777);
            assertEquals(s7777, StopManager.getInstance().getSelected());
        }
        catch(StopException e){
            fail("StopException was thrown ");
        }

        StopManager.getInstance().clearSelectedStop();
        assertEquals(null, StopManager.getInstance().getSelected());
    }
}
