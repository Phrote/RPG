package item;

import java.util.List;

public class Item {
    public String name;
    public int qty;

    public Item(String name, int qty) {
        this.name = name;
        this.qty = qty;
    }

    public List<String> getString() {
        return List.of(name, qty > 1 ? (String.valueOf(qty) + "x") : "");
    }

    @Override
    public String toString() {
        return qty + " " + name;
    }

//    @Override
//    public boolean equals(Object obj) {
//        return ((Item)obj).id == this.id && ((Item)obj).qty == this.qty;
//    }
}
