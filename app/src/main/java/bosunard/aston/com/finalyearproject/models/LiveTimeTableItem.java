package bosunard.aston.com.finalyearproject.models;

import java.io.Serializable;

public class LiveTimeTableItem implements Serializable {

    public String service;
    public String category;
    public String train_uid;
    public String platform;
    public String operator_name;
    public String aimed_departure_time;
    public String aimed_arrival_time;
    public String origin_name;
    public String source;
    public String destination_name;
    public String status;
    public String expected_arrival_time;
    public String expected_departure_time;
    public int best_arrival_estimate_mins;
    public int best_departure_estimate_mins;

    public LiveTimeTableItem(){}

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTrain_uid() {
        return train_uid;
    }

    public void setTrain_uid(String train_uid) {
        this.train_uid = train_uid;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getOperator() {
        return operator_name;
    }

    public void setOperator(String operator_name) {
        this.operator_name = operator_name;
    }

    public String getAimed_departure_time() {
        return aimed_departure_time;
    }

    public void setAimed_departure_time(String aimed_departure_time) {
        this.aimed_departure_time = aimed_departure_time;
    }

    public String getAimed_arrival_time() {
        return aimed_arrival_time;
    }

    public void setAimed_arrival_time(String aimed_arrival_time) {
        this.aimed_arrival_time = aimed_arrival_time;
    }

    public String getOrigin_name() {
        return origin_name;
    }

    public void setOrigin_name(String origin_name) {
        this.origin_name = origin_name;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination_name() {
        return destination_name;
    }

    public void setDestination_name(String destination_name) {
        this.destination_name = destination_name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getExpected_arrival_time() {
        return expected_arrival_time;
    }

    public void setExpected_arrival_time(String expected_arrival_time) {
        this.expected_arrival_time = expected_arrival_time;
    }

    public String getExpected_departure_time() {
        return expected_departure_time;
    }

    public void setExpected_departure_time(String expected_departure_time) {
        this.expected_departure_time = expected_departure_time;
    }

    public int getBest_arrival_estimate_mins() {
        return best_arrival_estimate_mins;
    }

    public void setBest_arrival_estimate_mins(int best_arrival_estimate_mins) {
        this.best_arrival_estimate_mins = best_arrival_estimate_mins;
    }

    public int getBest_departure_estimate_mins() {
        return best_departure_estimate_mins;
    }

    public void setBest_departure_estimate_mins(int best_departure_estimate_mins) {
        this.best_departure_estimate_mins = best_departure_estimate_mins;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }
}
