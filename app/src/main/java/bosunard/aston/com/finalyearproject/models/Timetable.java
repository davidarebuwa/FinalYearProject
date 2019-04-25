package bosunard.aston.com.finalyearproject.models;

import java.io.Serializable;
import java.util.List;

public class Timetable implements Serializable {

   // public String service;
    public String stops_of_interest;
    public String date;
    public String time_of_day;
    public String origin_name;
    public String destination_name;
    public String mode;
    public String category;
    public String request_of_time;
    public List<Stops> stops;


    public Timetable(){};

//    public String getService() {
//        return service;
//    }
//
//    public void setService(String service) {
//        this.service = service;
//    }

    public String getStops_of_interest() {
        return stops_of_interest;
    }

    public void setStops_of_interest(String stops_of_interest) {
        this.stops_of_interest = stops_of_interest;
    }

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

    public String getOrigin_name() {
        return origin_name;
    }

    public void setOrigin_name(String origin_name) {
        this.origin_name = origin_name;
    }

    public String getDestination_name() {
        return destination_name;
    }

    public void setDestination_name(String destination_name) {
        this.destination_name = destination_name;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getRequest_of_time() {
        return request_of_time;
    }

    public void setRequest_of_time(String request_of_time) {
        this.request_of_time = request_of_time;
    }

    public List<Stops> getStops() {
        return stops;
    }

    public void setStops(List<Stops> stops) {
        this.stops = stops;
    }
}
