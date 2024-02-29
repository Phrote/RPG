package item;

import java.util.List;

public class Item {
    public String id;
    public int qty;

    public Item(String id, int qty) {
        this.id = id;
        this.qty = qty;
    }

    public List<String> getString() {
        return List.of(ItemDatabase.getItemInfo(id).name, qty > 1 ? (String.valueOf(qty) + "x") : "");
    }

    @Override
    public String toString() {
        return qty + " " + ItemDatabase.getItemInfo(id).name;
    }

    @Override
    public boolean equals(Object obj) {
        return ((Item)obj).id == this.id && ((Item)obj).qty == this.qty;
    }
}
