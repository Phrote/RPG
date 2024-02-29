package classes.npc.villager;

import item.ItemDatabase;

import java.util.List;

public class Villager {

    public String id;

    public Villager(String id) {
        this.id = id;
    }

    public List<String> getString() {
        return List.of(ItemDatabase.getItemInfo(id).name);
    }
}
