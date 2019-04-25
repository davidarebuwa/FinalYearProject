package bosunard.aston.com.finalyearproject.models;

public class Stops {

    public String station_code;
    public String tiploc_code;
    public String station_name;
    public String aimed_arrival_date;
    public String aimed_arrival_time;
    public String aimed_departure_date;
    public String aimed_depature_time;
    public String stop_type;
    public String platform;

    public Stops(){}

    public String getStation_code() {
        return station_code;
    }

    public void setStation_code(String station_code) {
        this.station_code = station_code;
    }

    public String getTiploc_code() {
        return tiploc_code;
    }

    public void setTiploc_code(String tiploc_code) {
        this.tiploc_code = tiploc_code;
    }

    public String getStation_name() {
        return station_name;
    }

    public void setStation_name(String station_name) {
        this.station_name = station_name;
    }

    public String getAimed_arrival_date() {
        return aimed_arrival_date;
    }

    public void setAimed_arrival_date(String aimed_arrival_date) {
        this.aimed_arrival_date = aimed_arrival_date;
    }

    public String getAimed_arrival_time() {
        return aimed_arrival_time;
    }

    public void setAimed_arrival_time(String aimed_arrival_time) {
        this.aimed_arrival_time = aimed_arrival_time;
    }

    public String getAimed_departure_date() {
        return aimed_departure_date;
    }

    public void setAimed_departure_date(String aimed_departure_date) {
        this.aimed_departure_date = aimed_departure_date;
    }

    public String getAimed_depature_time() {
        return aimed_depature_time;
    }

    public void setAimed_depature_time(String aimed_depature_time) {
        this.aimed_depature_time = aimed_depature_time;
    }

    public String getStop_type() {
        return stop_type;
    }

    public void setStop_type(String stop_type) {
        this.stop_type = stop_type;
    }
}
