import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)

@JsonIgnoreProperties(ignoreUnknown = true)

public class LocationInfo {
    public RegionInfo RegionInfo;

}

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)

@JsonIgnoreProperties(ignoreUnknown = true)
class RegionInfo {

    private static final Logger logger = LogManager.getLogger(RegionInfo.class);

    public String Id;
    public Name Name;

    public String getDetail() {

        if (!Name.getEn().isEmpty() && Name.getEn() != null)
            return Name.getEn();

        if (!Name.getIt().isEmpty() && Name.getIt() != null)
            return Name.getIt();

        if (!Name.getDe().isEmpty() && Name.getDe() != null)
            return Name.getDe();

        return null;
    }

    public String getId() {

        String TrueId = null;
        try {
            TrueId = Id;
        } catch (NullPointerException e) {
            logger.error("ID is null or empty");
        }
        return TrueId;

    }
}

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)

@JsonIgnoreProperties(ignoreUnknown = true)
class Name {

    private static final Logger logger = LogManager.getLogger(Name.class);

    public String en;
    public String de;
    public String it;


    public String getEn() {

        String TrueEn = null;
        try {
            TrueEn = en;
        } catch (NullPointerException e) {
            logger.error("TrueEn is null or empty");
        }
        return TrueEn;

    }

    public String getIt() {
        String TrueIt = null;
        try {
            TrueIt = it;
        } catch (NullPointerException e) {
            logger.error("TrueIt is null or empty");
        }
        return TrueIt;
    }

    public String getDe() {
        String TrueDe = null;
        try {
            TrueDe = de;
        } catch (NullPointerException e) {
            logger.error("TrueDe is null or empty");
        }
        return TrueDe;
    }


}
