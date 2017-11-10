package ca.ubc.cs.cpsc210.translink.tests.parsers;

import ca.ubc.cs.cpsc210.translink.model.StopManager;
import ca.ubc.cs.cpsc210.translink.parsers.StopParser;
import ca.ubc.cs.cpsc210.translink.parsers.exception.StopDataMissingException;
import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


/**
 * Tests for the StopParser
 */

// TODO: Write more tests

public class StopParserTest {
    @BeforeEach
    public void setup() {
        StopManager.getInstance().clearStops();
    }

    @Test
    public void testStopParserNormal() throws StopDataMissingException, JSONException, IOException {
        StopParser p = new StopParser("stops.json");
        p.parse();
        assertEquals(8524, StopManager.getInstance().getNumStops());
    }
    @Test
    public void testStopParseWithSyntax(){
        StopParser p = new StopParser("stopParserWithWrongSyntax.json");
        try{
            p.parse();
            fail("JSONException should have been thrown");
        }catch(JSONException e){
            //expected
        }catch(IOException e){
            fail("IOException was thrown");
        }catch (StopDataMissingException e){
            fail("StopDataMissingException was thrown");
        }
    }

    @Test
    public void testStopDataMissing() {
        StopParser p = new StopParser("StopDataMissing.json");
        try{
            p.parse();
            fail("StopDataMissingException was thrown");
        }catch (StopDataMissingException e){
            //expected
        }catch(IOException e){
            fail("IOException was thrown");
        }catch (JSONException e) {
            fail("JSONException was thrown");
        }
    }
}
