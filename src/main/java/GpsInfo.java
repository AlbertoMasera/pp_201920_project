import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)

@JsonIgnoreProperties(ignoreUnknown = true)

public class GpsInfo {
    public String Gpstype;
}
