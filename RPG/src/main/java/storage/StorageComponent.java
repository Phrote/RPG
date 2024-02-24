package storage;

import main.Game;
import utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class StorageComponent {
    public int size;
    public List<Item> items;

    public StorageComponent(int size) {
        this.size = size;
        this.items = new ArrayList<Item>();
    }

    public Item place(Item item) {
        int remQty = item.qty;
        int stack = ItemDatabase.getItemInfo(item.id).maxStack;

        for(Item storedItem : items) {
            if(storedItem.id == item.id) {
                int qty = Math.min(remQty,stack - storedItem.qty);
                storedItem.qty += qty;
                remQty -= qty;
            }
            if(remQty == 0)
                return null;
        }

        while(remQty > 0 && items.size() < size) {
            int qty = Math.min(remQty,stack);
            items.add(new Item(item.id, qty));
            remQty -= qty;
        }
        Game.gui.updateInventoryGUI();
        return remQty > 0 ? new Item(item.id, remQty) : null;
    }

    public void remove(Item item) {
        int remQty = item.qty;
        var iter = items.listIterator(items.size());

        while(iter.hasPrevious()) {
            Item storedItem = iter.previous();
            if(storedItem.id == item.id) {
                if(storedItem.qty > remQty) {
                    storedItem.qty -= remQty;
                    remQty = 0;
                } else {
                    remQty -= storedItem.qty;
                    iter.remove();
                }
            }
            if(remQty == 0)
                return;
        }
    }

    public void transfer(StorageComponent target, Item item) {
        Item rem = target.place(item);
        if(rem == null) {
            this.remove(item);
        } else {
            this.remove(new Item(item.id, item.qty - rem.qty));
        }
        Game.gui.updateInventoryGUI();
    }

    //Convert text to Item struct.
    public Item textToItem(String text) {
        String[] parts = separateQty(text);
        String qtyStr = parts[0];
        String name = parts[1];
        String id = "";
        int storedQty = 0;

        for(Item item : items) {
            if(Utils.hammingClose(ItemDatabase.getItemInfo(item.id).name, name)) {
                storedQty += item.qty;
                id = item.id;
            }
        }
        if(storedQty == 0) {
            return null;
        }
        int qty = textToQty(qtyStr, storedQty);
        return qty == 0 ? null : new Item(id, qty);
    }

    //Utility function to get potential quantity part of a text.
    public static String[] separateQty(String text) {
        String[] prefixes = {"all", "all of", "every", "each", "half", "half of", "all but one", "third", "third of", "quarter", "quarter of", "a", "an"};
        String prefix = Utils.getBestPrefix(prefixes, text);
        if(prefix == null) {
            return text.split(" ", 2);
        } else {
            return new String[]{prefix, text.replace(prefix + " ","")};
        }
    }
    //Utility function to turn a quantity text into number based on the available quantity.
    public static int textToQty(String qty, int storedQty) {
        switch (qty) {
            case "a":
            case "an":
                return 1;
            case "all":
            case "every":
            case "each":
            case "all of":
                return storedQty;
            case "half of":
            case "half":
                return storedQty/2;
            case "third of":
            case "third":
                return storedQty/3;
            case "quarter of":
            case "quarter":
                return storedQty/4;
            case "all but one":
                return storedQty - 1;
            default:
                try {
                    return Math.min(storedQty,Integer.parseInt(qty));
                } catch (NumberFormatException e) {
                    return 0;
                }
        }
    }

    public int count(String id) {
        int storedQty = 0;
        for(Item item : items) {
            if(item.id == id) {
                storedQty += item.qty;
            }
        }
        return storedQty;
    }

    public int getFilledSlots() {
        return items.size();
    }

    public int getEmptySlots() {
        return size - items.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("----- Storage (size: " + size + ") -----\n");
        for(var v : items) {
            sb.append(v.toString() + "\n");
        }
        sb.append("----- Storage end -----\n");
        return sb.toString();
    }
}
