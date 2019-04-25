package bosunard.aston.com.finalyearproject.models;

import java.io.Serializable;

public class StationShort implements Serializable {

    public String type;
    public String name;
    public double latitude;
    public double longitude;
    public int accuracy;
    public String station_code;
    public String tiploc_code;
    public int distance;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

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


    @Override
    public String toString() {
        return "StationShort{" +
                "type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", station_code='" + station_code + '\'' +
                ", tiploc_code='" + tiploc_code + '\'' +
                '}';
    }
}
