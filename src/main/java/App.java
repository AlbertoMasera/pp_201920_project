import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class containing the Main method of the application
 *
 * @author Alberto Masera, Alessio Kerer, Michele Fiorese
 * @version 1.0
 */

public class App {

    private static final Logger logger = LogManager.getLogger(App.class);

    /**
     * MAIN METHOD
     * Calls all functions and logs each step of the program execution
     *
     * @param args args
     */
    public static void main(String[] args){

        logger.trace("Starting application");


        logger.trace("Starting setup");
        int inputNumber = Setup.setup();


        logger.trace("Starting extraction");
        String extractedData = Extraction.dataExtraction(inputNumber);


        logger.trace("Starting transformation");
        List<Activity> activity = Transformation.transformation(extractedData);


        logger.trace("Starting analysis");
        Analysis.analysis(activity);

        logger.trace("End of the execution");

    }
}
