package item;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class ToolDatabase {
    private static Map<String, ToolInfo> database = new HashMap<>();

    static {
        loadDatabase();
    }

    public static void loadDatabase() {
        try {
            Type type = new TypeToken<Map<String, ToolInfo>>(){}.getType();
            database = new Gson().fromJson(Files.readString(Paths.get("tools.json")), type);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static ToolInfo getToolInfo(String id) {
        return database.get(id);
    }
}
