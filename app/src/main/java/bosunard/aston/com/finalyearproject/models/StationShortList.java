package bosunard.aston.com.finalyearproject.models;

import java.util.ArrayList;
import java.util.List;

public class StationShortList {

    private String source;

    private List<StationShort> member;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public List<StationShort> getMember() {
        return member;
    }

    public void setMember(List<StationShort> member) {
        this.member = member;
    }

    public List<String> getStationNames(){

        List<String> members = new ArrayList<String>();
        for(StationShort station : member){

            members.add(station.toString());
        }
        return  members;

    }

}
