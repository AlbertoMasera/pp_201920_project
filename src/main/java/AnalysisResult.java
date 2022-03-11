import java.util.List;
import java.util.Map;

public class AnalysisResult {

    public Map<String, Integer> activiesType ;
    public List<String> trackedActivityIds;
    public SubRegion regionsWithMostActivities;
    public SubRegion regionsWithLeastActivities;

    public AnalysisResult(Map<String, Integer> activiesType, List<String> trackedActivityIds, SubRegion regionsWithMostActivities, SubRegion regionsWithLeastActivities) {
        this.activiesType = activiesType;
        this.trackedActivityIds = trackedActivityIds;
        this.regionsWithMostActivities = regionsWithMostActivities;
        this.regionsWithLeastActivities = regionsWithLeastActivities;
    }
}

class  SubRegion{
    public int numberOfActivities;
    public List<String> regionIds;


    public SubRegion(int numberOfActivities, List<String> regionIds) {
        this.numberOfActivities = numberOfActivities;
        this.regionIds = regionIds;
    }
}
