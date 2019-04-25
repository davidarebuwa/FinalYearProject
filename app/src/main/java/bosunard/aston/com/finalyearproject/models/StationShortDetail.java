package bosunard.aston.com.finalyearproject.models;

public class StationShortDetail {

    private StationShort member;

    public StationShort getMember() {
        return member;
    }

    public void setMember(StationShort member) {
        this.member = member;
    }

    @Override
    public String toString() {

        if (member != null) {

            return member.toString();

        }

        return super.toString();

    }

}
