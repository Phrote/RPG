package classes.npc.mob;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class MobDatabase {
    private static Map<String, MobInfo> database = new HashMap<>();

    static {
        loadDatabase();
    }

    public static void loadDatabase() {
        try {
            Type type = new TypeToken<Map<String, MobInfo>>(){}.getType();
            database = new Gson().fromJson(Files.readString(Paths.get("mobs.json")), type);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static MobInfo getMobInfo(String id) {
        return database.get(id);
    }
}
