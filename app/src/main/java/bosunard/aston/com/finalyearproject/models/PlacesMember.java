package bosunard.aston.com.finalyearproject.models;

public class PlacesMember {

    public String type;
    public String name;
    public String description;
    public String latitude;
    public String longitude;
    public String accuracy;
    public String station_code;
    public String tiploc_code;
    public String atcocode;
    public String distance;

    public PlacesMember(){}

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(String accuracy) {
        this.accuracy = accuracy;
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

    public String getAtcocode() {
        return atcocode;
    }

    public void setAtcocode(String atcocode) {
        this.atcocode = atcocode;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "PlacesMember{" +
                "type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", accuracy='" + accuracy + '\'' +
                ", station_code='" + station_code + '\'' +
                ", tiploc_code='" + tiploc_code + '\'' +
                ", atcocode='" + atcocode + '\'' +
                ", distance='" + distance + '\'' +
                '}';
    }
}
