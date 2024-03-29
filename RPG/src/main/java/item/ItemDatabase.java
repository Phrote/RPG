package item;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class ItemDatabase {
    private static Map<String, ItemInfo> database = new HashMap<>();

    static {
        loadDatabase();
    }

    public static void loadDatabase() {
        try {
            Type type = new TypeToken<Map<String, ItemInfo>>(){}.getType();
            database = new Gson().fromJson(Files.readString(Paths.get("items.json")), type);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static ItemInfo getItemInfo(String name) {
        return database.get(name);
    }
    public static boolean isItem(String name) { return database.containsKey(name); }
}
