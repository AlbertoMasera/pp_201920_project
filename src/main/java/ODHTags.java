import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)

@JsonIgnoreProperties(ignoreUnknown = true)

public class ODHTags {

    private static final Logger logger = LogManager.getLogger(ODHTags.class);

    private String Id;

    public String getId() {

        String TrueId = null;
        try { TrueId = Id; } catch (NullPointerException e){
            logger.error("ID is null or empty");
        }
        return TrueId;
    }
}
