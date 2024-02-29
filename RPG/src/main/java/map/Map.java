package map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Map {
    public static HashMap<String, AreaInfo> areaDatabase;
    public static HashMap<String, VillageInfo> villageDatabase;

    static {
        loadDatabase();
//        generateTemplate();
    }

    public static void loadDatabase() {
        try {
            Type areaType = new TypeToken<java.util.HashMap<String, AreaInfo>>(){}.getType();
            areaDatabase = new Gson().fromJson(Files.readString(Paths.get("areas.json")), areaType);
            Type villageType = new TypeToken<java.util.HashMap<String, VillageInfo>>(){}.getType();
            villageDatabase = new Gson().fromJson(Files.readString(Paths.get("villages.json")), villageType);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void generateTemplate() {
        areaDatabase = new HashMap<>();
        villageDatabase = new HashMap<>();
        areaDatabase.put("first_forest", new AreaInfo("First forest", "Wonderful forest next to the first village", new Node(2,1), List.of(new AreaGatherable("leather",5))));
        villageDatabase.put("first_village", new VillageInfo("First village", "The very first village that was created in the land", new Node(0,0)));

        try {
            Files.writeString(Paths.get("areas_template.json"), new Gson().newBuilder().setPrettyPrinting().create().toJson(areaDatabase));
            Files.writeString(Paths.get("villages_template.json"), new Gson().newBuilder().setPrettyPrinting().create().toJson(villageDatabase));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static PlaceInfoBase getPlaceInfo(String id) {
        PlaceInfoBase place;
        place = areaDatabase.get(id);
        if(place != null) {
            return place;
        }
        place = villageDatabase.get(id);

        if(place != null) {
            return place;
        }

        return null;
    }
    public static AreaInfo getAreaInfo(String id) {
        return areaDatabase.get(id);
    }
    public static VillageInfo getVillageInfo(String id) {
        return villageDatabase.get(id);
    }

    public static List<String> getPlacesInRange(String from, int dist) {
        ArrayList<String> places = new ArrayList<>();

        PlaceInfoBase fromInfo = getPlaceInfo(from);
        if(fromInfo == null) {
            return null;
        }

        for(var placeEntry : areaDatabase.entrySet()) {
            if(fromInfo.node.isWithin(placeEntry.getValue().node, dist)) {
                places.add(placeEntry.getKey());
            }
        }

        for(var placeEntry : villageDatabase.entrySet()) {
            if(fromInfo.node.isWithin(placeEntry.getValue().node, dist)) {
                places.add(placeEntry.getKey());
            }
        }

        return places;
    }
}
