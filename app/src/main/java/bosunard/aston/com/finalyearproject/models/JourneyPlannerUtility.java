package bosunard.aston.com.finalyearproject.models;

import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static android.support.constraint.Constraints.TAG;

public class JourneyPlannerUtility {

    public static JourneyPlanner planJourney(String uri, String referer) {


        HttpURLConnection conn = null;
        StringBuilder jsonResults = new StringBuilder();
        try {
            URL url = new URL(uri);
            Log.i(TAG, url.toString());
            conn = (HttpURLConnection) url.openConnection();
            if (referer != null) {
                conn.setRequestProperty("Referer", referer);
            }
            InputStreamReader in = new InputStreamReader(conn.getInputStream());
            Gson gson = new Gson();
            JourneyPlanner jpl = gson.fromJson(in, JourneyPlanner.class);
            Log.i(TAG, "We got " + jpl.getRoutes().size() + " journeys");
            // Load  results into a StringBuilder

            return jpl;
        } catch (MalformedURLException e) {
            Log.i(TAG, "Error processing TransportAPI URL");
            return null;
        } catch (IOException e) {
            Log.i(TAG, "Error connecting to TransportAPI: " + e.getMessage());
            return null;
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
    }
}
