import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Analysis {

    private static final Logger logger = LogManager.getLogger(Analysis.class);

    public static void analysis(List<Activity> activities) {

        ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
        List<String> ids = new ArrayList<>();
        List<String> regionIds = new ArrayList<>();
        List<String> types = new ArrayList<>();

        for (Activity ar : activities) {
            ids.add(ar.getId());
            regionIds.add(ar.getRegionId());
            List<String> type = ar.getTypes();
            types.addAll(type);
        }

        Map<String, Integer> countMapTypes = createMap(types);

        Map<String, Integer> countMapRegionIds = createMap(regionIds);

        int max = Collections.max(countMapRegionIds.values());
        int min = Collections.min(countMapRegionIds.values());

        List<String> idMax = listIterator(max, countMapRegionIds);
        List<String> idMin = listIterator(min, countMapRegionIds);

        SubRegion most = new SubRegion(max, idMax);
        SubRegion less = new SubRegion(min, idMin);

        AnalysisResult a = new AnalysisResult(countMapTypes, ids, most, less);

        try {
            mapper.writeValue(new File("results/analysis.json"), a);
            Validator.isValid("src/main/resources/analysis.schema.json","results/analysis.json" );

        } catch (IOException e) {
            logger.error("Can't write in the file");
        }
    }

    private static Map<String, Integer> createMap(List<String> ids) {

        Map<String, Integer> countMap = new HashMap<>();

        for (String id : ids) {

            if (countMap.containsKey(id))
                countMap.put(id, countMap.get(id) + 1);
            else
                countMap.put(id, 1);
        }
        return countMap;
    }

    private static List<String> listIterator(int maxOrMin, Map<String, Integer> countMapRegionIds) {
        List<String> id = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : countMapRegionIds.entrySet())
            if (entry.getValue() == maxOrMin)
                id.add(entry.getKey());

        return id;
    }
}