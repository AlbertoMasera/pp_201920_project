import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The class Transformation.java contains the method to transform the previously extracted data and return it as a List
 * of Activity objects. It also maps the results into json objects in files with their IDs as file name.
 *
 * @author Alberto Masera, Alessio Kerer, Michele Fiorese
 */

public class Transformation {

    private static final Logger logger = LogManager.getLogger(Transformation.class);

    /**
     * The transformation method serializes the data it recives as string and puts them into a List.
     *
     * @param extractedData of previously extracted Activity data
     * @return List of Activity objects
     */

    public static List<Activity> transformation(String extractedData) {

        List<ActivityResult> activityResults = new ArrayList<>();
        List<Activity> activities = new ArrayList<>();

        try {
            ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

            JsonNode node = mapper.readTree(extractedData);
            JsonNode items = node.get("Items");

            activities = mapper.readValue(items.traverse(), new TypeReference<ArrayList<Activity>>() {});


            for(Activity a: activities){
                activityResults.add(Activity.ActivityResultGenerator(a));
            }

            for(ActivityResult a: activityResults)
                if (a!=null){
                    mapper.writeValue(new File("results/Activity_"+a.getId()+".json"), a);
                    Validator.isValid("src/main/resources/Activity.schema.json","results/Activity_"+a.getId()+".json" );
                }

        } catch (JsonProcessingException e) {
            logger.error("Error with the processing of json object");
        } catch (IOException e) {
            logger.error("I/O problem occurred");
        }

        return activities;
    }

}
