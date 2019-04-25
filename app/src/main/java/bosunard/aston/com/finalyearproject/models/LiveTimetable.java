package bosunard.aston.com.finalyearproject.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class LiveTimetable implements Serializable {

    public String date;
    public String time_of_day;
    public String  station_name;
    public String station_code;

   // @SerializedName("departures")
    public Departures departures;

    public LiveTimetable(){}

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime_of_day() {
        return time_of_day;
    }

    public void setTime_of_day(String time_of_day) {
        this.time_of_day = time_of_day;
    }

    public String getStation_name() {
        return station_name;
    }

    public void setStation_name(String station_name) {
        this.station_name = station_name;
    }

    public String getStation_code() {
        return station_code;
    }

    public void setStation_code(String station_code) {
        this.station_code = station_code;
    }

    public Departures getDepartures() {
        return departures;
    }


    //    public LiveTimeTableItem[] getAll() {
//        return all;
//    }
//
//    public void setAll(LiveTimeTableItem[] all) {
//        this.all = all;
//    }


    // public void setAll(List<LiveTimeTableItem> all) {
//        this.all = all;
//    }
}
