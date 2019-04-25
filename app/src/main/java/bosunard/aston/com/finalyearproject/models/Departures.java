package bosunard.aston.com.finalyearproject.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Departures implements Serializable {

    @SerializedName("all")
    public List<LiveTimeTableItem> all;

    public Departures() {
    }

    public List<LiveTimeTableItem> getAll() {
        return all;
    }
}
