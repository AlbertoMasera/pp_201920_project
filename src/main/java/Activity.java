import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)

@JsonIgnoreProperties(ignoreUnknown = true)

/**
 * The Activity.java helps creating an ActivityResult object for each activity containing the right information for
 * ID, Title, Base Text, Types, GPS Track and Detail.
 *
 * @author Alberto Masera, Alessio Kerer, Michele Fiorese
 */

public class Activity {

    private static final Logger logger = LogManager.getLogger(Activity.class);

    private String Id;
    private Detail Detail;
    private List<ODHTags> ODHTags;
    private List<GpsInfo> GpsInfo;
    private List<GpsTrack> GpsTrack;
    private LocationInfo LocationInfo;
    private GpsPoints GpsPoints;


    /**
     * This methods calls all the other methods in the class to construct the ActivityResult with the correct
     * information.
     *
     * @param activity An Activity object to help construct the result
     * @return Returns the completed AcrivityResult object
     */
    public static ActivityResult ActivityResultGenerator(Activity activity) {
        ActivityResult a = null;
        try {
            a = new ActivityResult(activity.getId(), activity.Detail.getTitle(), activity.Detail.getBaseText(),
                    activity.getTypes(), hasGPSTrack(activity), activity.LocationInfo.RegionInfo.getDetail());
        } catch (NullPointerException e) {
            logger.error("An activity has not the needed information");
        }

        return a;

    }

    /**
     * This method checks if there is a GPS Track, Point or Info for this specific Activity
     *
     * @param activity Instance of an Activity
     * @return Returns boolean value if the activity has any type of GPS data
     * @throws NullPointerException thrown when an application attempts to use null in a case where an object is required
     */
    public static boolean hasGPSTrack(Activity activity) {
        boolean a = false;
        boolean b = false;
        boolean c = false;
        try {
            a = (activity.GpsPoints.position != null) && (!activity.GpsPoints.position.isEmpty());
        } catch (NullPointerException e) {
            logger.error("GPSPoints is null or empty");
        }
        try {
            b = (activity.GpsInfo != null) && (!activity.GpsInfo.isEmpty());
        } catch (NullPointerException e) {
            logger.error("GpsInfo is null or empty");
        }
        try {
            c = (activity.GpsTrack != null) && (!activity.GpsTrack.isEmpty());
            ;
        } catch (NullPointerException e) {
            logger.error("GpsTrack is null or empty");
        }

        return a || b || c;
    }

    /**
     * Method to read the Activity's ID
     *
     * @return Returns the ID
     * @throws NullPointerException is thrown when program attempts to use an object reference that has the null value
     */
    public String getId() {

        String TrueId = null;
        try {
            TrueId = Id;
        } catch (NullPointerException e) {
            logger.error("ID is null or empty");
        }

        return TrueId;
    }

    /**
     * Method to make a List containing activity types
     *
     * @return Returns a List of every Type
     */
    public List<String> getTypes() {

        List<String> typesList = new ArrayList<>();

        for (ODHTags odh : ODHTags)
            typesList.add(odh.getId());

        return typesList;
    }

    /**
     * Method to find and return the region ID
     *
     * @return Returns the ID
     * @throws NullPointerException is thrown when program attempts to use an object reference that has the null value
     */
    public String getRegionId() {

        String TrueId = null;
        try {
            TrueId = LocationInfo.RegionInfo.getId();
        } catch (NullPointerException e) {
            logger.error("ID is null or empty");
        }

        return TrueId;
    }

}



