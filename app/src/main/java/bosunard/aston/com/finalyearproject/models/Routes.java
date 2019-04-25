package bosunard.aston.com.finalyearproject.models;

import java.util.List;

public class Routes {

    public List<String> duration;
    public List<RouteParts> route_parts;

    public Routes(){}

    public List<String> getDuration() {
        return duration;
    }

    public void setDuration(List<String> duration) {
        this.duration = duration;
    }

    public List<RouteParts> getRoute_parts() {
        return route_parts;
    }

    public void setRoute_parts(List<RouteParts> route_parts) {
        this.route_parts = route_parts;
    }
}
