import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * 
 * The Setup.java class is the class that reads the number from the input file and returns it to the rest of the
 * program for later uses. The file in 'src/main/resources/input.txt' is opened with the BufferedReader class, the
 * first line is stored to a variable which then gets parsed to an Integer and then returned at the end of the method
 *
 * @author Alberto Masera, Alessio Kerer, Michele Fiorese
 */

public class Setup {

    private static final Logger logger = LogManager.getLogger(Setup.class);

    /**
     * The setup method reads the number from the input file and returns it as integer.
     *
     * @return Returns the number read from the file
     */

    public static int setup() {

        int result = -1;

        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/input.txt"));
            String data = reader.readLine();
            while (data != null) {
                result=Integer.parseInt(data);
                data = reader.readLine();
            }
            reader.close();


        } catch (FileNotFoundException e) {
            logger.fatal("File not found");

        } catch (NumberFormatException e) {
            logger.fatal("The input file doesn't contain a number");

            System.exit(0);

        } catch (IOException e) {
            logger.error("I/O error occured");
        }

        return result;
    }
}
