package storage;

import java.util.HashMap;
import java.util.Map;

public class ItemDatabase {
    private static Map<String, ItemInfo> database = new HashMap<>();

    static {
        loadDatabase();

    }

    public static void loadDatabase() {
        //Load JSON later
        database.put("test",new ItemInfo("Test item", "A precious test item", 1));
        database.put("log",new ItemInfo("Log", "A wooden log", 10));
        database.put("rice",new ItemInfo("Rice", "Rice", 20));
    }
    public static ItemInfo getItemInfo(String id) {
        return database.get(id);
    }
//    public static Item getItemFromName(String name, int qty) {
//        for (var entry : database.entrySet()) {
//            if(entry.getValue().name == name) {
//                return new Item(entry.getKey(),qty);
//            }
//        }
//        return null;
//    }
}
