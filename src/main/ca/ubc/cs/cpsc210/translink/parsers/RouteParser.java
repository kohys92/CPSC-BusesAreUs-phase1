package ca.ubc.cs.cpsc210.translink.parsers;

import ca.ubc.cs.cpsc210.translink.model.Route;
import ca.ubc.cs.cpsc210.translink.model.RouteManager;
import ca.ubc.cs.cpsc210.translink.parsers.exception.RouteDataMissingException;
import ca.ubc.cs.cpsc210.translink.providers.DataProvider;
import ca.ubc.cs.cpsc210.translink.providers.FileDataProvider;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Parse route information in JSON format.
 */
public class RouteParser {
    private String filename;

    public RouteParser(String filename) {
        this.filename = filename;
    }
    /**
     * Parse route data from the file and add all route to the route manager.
     *
     */
    public void parse() throws IOException, RouteDataMissingException, JSONException{
        DataProvider dataProvider = new FileDataProvider(filename);

        parseRoutes(dataProvider.dataSourceToString());
    }
    /**
     * Parse route information from JSON response produced by Translink.
     * Stores all routes and route patterns found in the RouteManager.
     *
     * @param  jsonResponse    string encoding JSON data to be parsed
     * @throws JSONException   when:
     * <ul>
     *     <li>JSON data does not have expected format (JSON syntax problem)
     *     <li>JSON data is not an array
     * </ul>
     * If a JSONException is thrown, no routes should be added to the route manager
     *
     * @throws RouteDataMissingException when
     * <ul>
     *  <li>JSON data is missing RouteNo, Name, or Patterns element for any route</li>
     *  <li>The value of the Patterns element is not an array for any route</li>
     *  <li>JSON data is missing PatternNo, Destination, or Direction element for any route pattern</li>
     * </ul>
     * If a RouteDataMissingException is thrown, all correct routes are first added to the route manager.
     */

    public void parseRoutes(String jsonResponse)
            throws JSONException, RouteDataMissingException {
        // TODO: Task 4: Implement this method
        JSONArray jsonArray = new JSONArray(jsonResponse);
        RouteManager routeManager = RouteManager.getInstance();
        StringBuilder msg = new StringBuilder();

        for (int i = 0; i < jsonArray.length(); ++i) {
            String routeNumber = "";
            try {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                routeNumber = jsonObject.getString("RouteNo");

                String routeName = jsonObject.getString("Name");
                Route route = routeManager.getRouteWithNumber(routeNumber, routeName);

                JSONArray patterns = jsonObject.getJSONArray("Patterns");

                for (int k = 0; k < patterns.length(); k++) {
                    JSONObject routePattern = patterns.getJSONObject(k);

                    String patternNumber = routePattern.getString("PatternNo");
                    String patternDestination = routePattern.getString("Destination");
                    String patternDirection = routePattern.getString("Direction");

                    route.getPattern(patternNumber, patternDestination, patternDirection);
                }

            } catch (JSONException e) {
                msg.append(routeNumber.length() > 0 ? routeNumber : "Unnumbered route");
                msg.append(" ");
            }
        }

        if (msg.length() > 0)
            throw new RouteDataMissingException("Missing required data about routes: " + msg.toString());
    }
}
