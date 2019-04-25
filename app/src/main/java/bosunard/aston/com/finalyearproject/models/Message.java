package bosunard.aston.com.finalyearproject.models;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class Message {

    private String sender;
   // private String reciever;
    private String message;
    public String time;

    public Message(String sender, String time, String message) {
        this.sender = sender;
        //this.reciever = reciever;
        this.message = message;
        this.time = time;

    }

    public Message(){}


    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("sender", sender);
        result.put("message", message);
        result.put("time", time);

        return result;
    }


    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
