package item;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class ArmourDatabase {
    private static Map<String, ArmourInfo> database = new HashMap<>();

    static {
        loadDatabase();
    }

    public static void loadDatabase() {
        try {
            Type type = new TypeToken<Map<String, ArmourInfo>>(){}.getType();
            database = new Gson().fromJson(Files.readString(Paths.get("armours.json")), type);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static ArmourInfo getArmourInfo(String id) {
        return database.get(id);
    }
}
