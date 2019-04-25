package bosunard.aston.com.finalyearproject.models;

public class Updates {

    public int thumbnail;
    public String trainServiceName;
    public String comment;


    public Updates(int thumbnail, String trainServiceName, String comment) {
        this.thumbnail = thumbnail;
        this.trainServiceName = trainServiceName;
        this.comment = comment;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }

    public void setTrainServiceName(String trainServiceName) {
        this.trainServiceName = trainServiceName;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public String getTrainServiceName() {
        return trainServiceName;
    }

    public String getComment() {
        return comment;
    }
}
