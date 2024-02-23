package classes.npc.mob;

import storage.ItemDatabase;

import java.util.List;

public class Mob {
    public String id;
    public int qty;

    public Mob(String id, int qty) {
        this.id = id;
        this.qty = qty;
    }

    public List<String> getString() {
        return List.of(ItemDatabase.getItemInfo(id).name, qty > 1 ? (String.valueOf(qty) + "x") : "");
    }

}
