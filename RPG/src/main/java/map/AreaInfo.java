package map;

import java.util.ArrayList;
import java.util.List;

public class AreaInfo extends PlaceInfoBase{
    public List<AreaGatherable> gatherables;

    public AreaInfo(String name, String description, Node node, List<AreaGatherable> gatherables) {
        super(name, description, node);
        this.gatherables = gatherables;
    }
}
