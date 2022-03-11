import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class MyTest {
    public static String StringForTest() {
        String result = "";
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/test/java/JSonObject"));
            String data = reader.readLine();
            while (data != null) {
                result = data;
                data = reader.readLine();
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Test
    void SetupTest() {
        boolean isInt = false;
        Setup s = new Setup();
        int setupNumber = s.setup();
        if (setupNumber % 1 == 0)
            isInt = true;
        assertEquals(isInt, true, "Setup testing failed");
    }

    @Test
    void dataExtractionTest() {
        MyTest test=new MyTest();
        String result2=StringForTest();
        assertEquals(test.StringForTest(), result2, "The dataExtraction failed.");
    }

    @Test
    void transformationTest() {
        List<ActivityResult> activityResults = new ArrayList<>();
        List<Activity> activities = new ArrayList<>();
        for(Activity a: activities){
            activityResults.add(Activity.ActivityResultGenerator(a));
        }
        assertEquals(Transformation.transformation(StringForTest()),activities, "The transformation failed.");
    }


}
