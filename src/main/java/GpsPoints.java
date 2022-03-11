import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)

@JsonIgnoreProperties(ignoreUnknown = true)

public class GpsPoints {
    public Position position;

}

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)

@JsonIgnoreProperties(ignoreUnknown = true)

class Position {
    public String Gpstype;

    public boolean isEmpty() {
        if (Gpstype==null)
            return true;
        return false;
    }
}