package item;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ItemInfo {
    public String name;
    public String description;
    public int maxStack;

    public Set<String> tags;
    public Map<String, String> data;

    public ItemInfo(String name, String description, int maxStack) {
        this.name = name;
        this.description = description;
        this.maxStack = maxStack;
        this.tags = new HashSet<>();
        this.data = new HashMap<>();
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }

    public void setData(Map<String, String> data) {
        this.data = data;
    }

    public boolean hasTag(String tag) {
        return this.tags.contains(tag);
    }

    public String getData(String dataKey) {
        return this.data.get(dataKey);
    }

    public int getIntData(String dataKey) {
        return Integer.parseInt(this.data.get(dataKey));
    }
}
