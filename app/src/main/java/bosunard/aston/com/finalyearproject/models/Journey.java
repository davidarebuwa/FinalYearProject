package bosunard.aston.com.finalyearproject.models;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.firestore.ServerTimestamp;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class Journey implements Serializable {

    private String departingTime;
    private String arrivingTime;
    private String departingStation;
    private String arrivingStation;
    private String trainService;
    private String trainDestination;
    private String platformNumber;
    private String journeyDuration;
    private int noOfStops;
    private @ServerTimestamp Date timestamp;
    private String userId;

    public Journey(){}

    public Journey(String departingTime, String arrivingTime, String departingStation, String arrivingStation, String trainService, String trainDestination, String platformNumber, String journeyDuration, int noOfStops, Date timestamp) {
        this.departingTime = departingTime;
        this.arrivingTime = arrivingTime;
        this.departingStation = departingStation;
        this.arrivingStation = arrivingStation;
        this.trainService = trainService;
        this.trainDestination = trainDestination;
        this.platformNumber = platformNumber;
        this.journeyDuration = journeyDuration;
        this.timestamp = timestamp;

    }

    public String getDepartingTime() {
        return departingTime;
    }

    public void setDepartingTime(String departingTime) {
        this.departingTime = departingTime;
    }

    public String getArrivingTime() {
        return arrivingTime;
    }

    public void setArrivingTime(String arrivingTime) {
        this.arrivingTime = arrivingTime;
    }

    public String getDepartingStation() {
        return departingStation;
    }

    public void setDepartingStation(String departingStation) {
        this.departingStation = departingStation;
    }

    public String getArrivingStation() {
        return arrivingStation;
    }

    public void setArrivingStation(String arrivingStation) {
        this.arrivingStation = arrivingStation;
    }

    public String getTrainService() {
        return trainService;
    }

    public void setTrainService(String trainService) {
        this.trainService = trainService;
    }

    public String getTrainDestination() {
        return trainDestination;
    }

    public void setTrainDestination(String trainDestination) {
        this.trainDestination = trainDestination;
    }

    public String getPlatformNumber() {
        return platformNumber;
    }

    public void setPlatformNumber(String platformNumber) {
        this.platformNumber = platformNumber;
    }

    public String getJourneyDuration() {
        return journeyDuration;
    }

    public void setJourneyDuration(String journeyDuration) {
        this.journeyDuration = journeyDuration;
    }

    public int getNoOfStops() {
        return noOfStops;
    }

    public void setNoOfStops(int noOfStops) {
        this.noOfStops = noOfStops;
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("arrivingStation", arrivingStation);
        result.put("arrivingTime", arrivingTime);
        result.put("departingStation", departingStation);
        result.put("departingTime", departingTime);
        result.put("journeyDuration", journeyDuration);
        result.put("noOfStops", noOfStops);
        result.put("platformNumber", platformNumber);
        result.put("trainDestination", trainDestination);
        result.put("trainService", trainService);
        result.put("userId", userId);


        return result;
    }

    @Override
    public String toString() {
        return "Journey{" +
                "departingTime='" + departingTime + '\'' +
                ", arrivingTime='" + arrivingTime + '\'' +
                ", departingStation='" + departingStation + '\'' +
                ", arrivingStation='" + arrivingStation + '\'' +
                ", trainService='" + trainService + '\'' +
                ", trainDestination='" + trainDestination + '\'' +
                ", platformNumber='" + platformNumber + '\'' +
                ", journeyDuration='" + journeyDuration + '\'' +
                ", noOfStops=" + noOfStops +
                '}';
    }
}
