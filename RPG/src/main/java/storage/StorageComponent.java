package storage;

import java.util.ArrayList;
import java.util.List;

public class StorageComponent {
    public int size;
    public List<Item> items;

    public StorageComponent(int size) {
        this.size = size;
        this.items = new ArrayList<Item>();
    }

    public boolean hasPlace(Item item) {
        int remQty = item.qty;
        int stack = ItemDatabase.getItemInfo(item.id).maxStack;

        for(Item storedItem : items) {
            if(storedItem.id == item.id) {
                remQty -= Math.min(remQty,stack - storedItem.qty);
            }
            if(remQty == 0)
                return true;
        }
        return size - items.size() >= (remQty / stack) + (remQty % stack != 0 ? 1 : 0);
    }

    public void place(Item item) {
        int remQty = item.qty;
        int stack = ItemDatabase.getItemInfo(item.id).maxStack;

        for(Item storedItem : items) {
            if(storedItem.id == item.id) {
                int qty = Math.min(remQty,stack - storedItem.qty);
                storedItem.qty += qty;
                remQty -= qty;
            }
            if(remQty == 0)
                return;
        }

        while(remQty > 0) {
            int qty = Math.min(remQty,stack);
            items.add(new Item(item.id, qty));
            remQty -= qty;
        }
    }
}
