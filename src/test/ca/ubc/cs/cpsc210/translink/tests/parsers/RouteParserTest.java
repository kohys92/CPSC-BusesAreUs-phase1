package ca.ubc.cs.cpsc210.translink.tests.parsers;

import ca.ubc.cs.cpsc210.translink.model.RouteManager;
import ca.ubc.cs.cpsc210.translink.parsers.RouteParser;
import ca.ubc.cs.cpsc210.translink.parsers.exception.RouteDataMissingException;
import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Tests for the RouteParser
 */
// TODO: Write more tests

public class RouteParserTest {
    @BeforeEach
    public void setup() {
        RouteManager.getInstance().clearRoutes();
    }

    @Test
    public void testRouteParserNormal() throws RouteDataMissingException, JSONException, IOException {
        RouteParser p = new RouteParser("allroutes.json");
        p.parse();
        assertEquals(229, RouteManager.getInstance().getNumRoutes());
    }

    @Test
    public void testRouteParseWithSyntax() {
        RouteParser p = new RouteParser("routeParserWithWrongSyntax.json");
        try{
            p.parse();
            fail("JSONException should have been thrown");
        }catch(JSONException e){
            //expected
        }catch(IOException e){
            fail("IOException was thrown");
        }catch (RouteDataMissingException e){
            fail("RouteDataMissingException was thrown");
        }
    }

    @Test
    public void testRouteDataMissing(){
        RouteParser p = new RouteParser("routeDataMissing.json");
        try{
            p.parse();
            fail("RouteDataMissingException should have been thrown");
        }catch(RouteDataMissingException e){
            //expected
        }catch(IOException e){
            fail("IOException was thrown");
        }catch (JSONException e){
            fail("JSONException should have been thrown");
        }
    }
}
