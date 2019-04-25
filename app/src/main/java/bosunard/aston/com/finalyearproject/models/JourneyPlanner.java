package bosunard.aston.com.finalyearproject.models;

import java.io.Serializable;
import java.util.List;

public class JourneyPlanner implements Serializable {

    public String source;
    public String acknowledgements;
    public List<Routes> routes;

    public JourneyPlanner(){ }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getAcknowledgements() {
        return acknowledgements;
    }

    public void setAcknowledgements(String acknowledgements) {
        this.acknowledgements = acknowledgements;
    }

    public List<Routes> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Routes> routes) {
        this.routes = routes;
    }
}
