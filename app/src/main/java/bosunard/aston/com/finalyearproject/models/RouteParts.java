package bosunard.aston.com.finalyearproject.models;

import java.util.List;

public class RouteParts {


    public String mode;
    public String from_point_name;
    public String to_point_name;
    public String destination;
    public String line_name;
    public String duration;
    public String departure_time;
    public String arrival_time;
    public List<Coordinates> coordinates;

    public RouteParts(){}

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getFrom_point_name() {
        return from_point_name;
    }

    public void setFrom_point_name(String from_point_name) {
        this.from_point_name = from_point_name;
    }

    public String getTo_point_name() {
        return to_point_name;
    }

    public void setTo_point_name(String to_point_name) {
        this.to_point_name = to_point_name;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getLine_name() {
        return line_name;
    }

    public void setLine_name(String line_name) {
        this.line_name = line_name;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDeparture_time() {
        return departure_time;
    }

    public void setDeparture_time(String departure_time) {
        this.departure_time = departure_time;
    }

    public String getArrival_time() {
        return arrival_time;
    }

    public void setArrival_time(String arrival_time) {
        this.arrival_time = arrival_time;
    }

    public List<Coordinates> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<Coordinates> coordinates) {
        this.coordinates = coordinates;
    }
}
