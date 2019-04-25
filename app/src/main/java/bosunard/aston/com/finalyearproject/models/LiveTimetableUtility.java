package bosunard.aston.com.finalyearproject.models;

import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import static android.support.constraint.Constraints.TAG;

public class LiveTimetableUtility {

    public static final String TAG = "LiveTimetableUtility";

    public static LiveTimetable findResults(String uri, String referer) {

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
            LiveTimetable timetable = gson.fromJson(in, LiveTimetable.class);

            Log.i(TAG, "We got " + timetable.departures.all.size() + "results");

            return timetable;
        } catch (MalformedURLException e) {
            Log.i(TAG, "Error processing TransportAPI URL" + e.getMessage());
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
