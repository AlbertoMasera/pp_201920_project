import java.util.List;

public class ActivityResult {
    public String id;
    public String name;
    public String description;
    public List<String> types;
    public boolean hasGPSTrack;
    public String region;

    public ActivityResult(String id, String name, String description, List<String> types, boolean hasGPSTrack, String region) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.types = types;
        this.hasGPSTrack = hasGPSTrack;
        this.region = region;
    }


    public String getId() {
        return id;
    }

}