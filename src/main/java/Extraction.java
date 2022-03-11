import java.io.IOException;

import org.apache.http.client.fluent.Request;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * The class Extractio.java is the class that is called to request a certain number of objects from the opendatahub API.
 * The request is made to the url while embedding the number of objects we are requesting. If no problems occur,
 * the data is returned as a String to the program.
 *
 * @author Alberto Masera, Alessio Kerer, Michele Fiorese
 */

public class Extraction {

    private static final Logger logger = LogManager.getLogger(Extraction.class);

    /**
     * The dataExtraction method embeds the number of objects into the URL, makes a request and obtains the String of
     * data.
     *
     * @param n integer, number of objects we want to request
     * @return Returns the String of data requested from the API
     */

    public static String dataExtraction(int n) {

        String content = "";

        try {
            content = Request.Get("https://tourism.opendatahub.bz.it/api/Activity?pagenumber=1&pagesize=" +
                    n + "&activitytype=1023")

                    /*
                    A connection timeout occurs only upon starting the TCP connection.
                    This usually happens if the remote machine does not answer.
                    This means that the server has been shut down, you used the wrong IP/DNS name,
                    wrong port or the network connection to the server is down.

                    A socket timeout is dedicated to monitor the continuous incoming data flow.
                    If the data flow is interrupted for the specified timeout the connection is regarded as stalled/broken.
                    Of course this only works with connections where data is received all the time.
                     */

                    .connectTimeout(3000)
                    .socketTimeout(3000)
                    .execute()
                    .returnContent()
                    .asString();

        } catch (IOException e) {
            logger.error("Cannot retrive data, perhaps for connection issues");
        }
        return content;

    }

}
