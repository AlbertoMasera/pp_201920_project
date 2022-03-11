import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonIgnoreProperties(ignoreUnknown = true)
class Detail {

    private SubDetail de;
    private SubDetail en;
    private SubDetail it;

    public String getBaseText() {

        if (en != null)
            return en.getText();

        if (it != null)
            return it.getText();

        if (de != null)
            return de.getText();

        return null;
    }

    public String getTitle() {

        if (en != null)
            return en.getTitle();

        if (it != null)
            return it.getTitle();

        if (de != null)
            return de.getTitle();

        return null;
    }

}

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonIgnoreProperties(ignoreUnknown = true)
class SubDetail {

    private static final Logger logger = LogManager.getLogger(SubDetail.class);

    private String Title;
    private String BaseText;

    public String getText() {

        String TrueBaseText = null;
        try {
            TrueBaseText = BaseText.replaceAll("<[^>]*>", "");
            TrueBaseText = TrueBaseText.replaceAll("&(.*)", "").replaceAll("\\r\\n", "");

        } catch (NullPointerException e) {
            logger.error("Can't use regular expression, Base Text is null");
        }
        return TrueBaseText;
    }

    public String getTitle() {

        String TrueTitle = null;
        try {
            TrueTitle = Title;
        } catch (NullPointerException e) {
            logger.error("Title is null or empty");
        }
        return TrueTitle;
    }
}