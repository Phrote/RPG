package classes.npc;

import utils.Utils;
import item.Item;

import java.util.ArrayList;

public class DropTable {
    public ArrayList<DropInfo> dropTable;
    public DropTable() {
        dropTable = new ArrayList<>();
    }

    public ArrayList<Item> drop() {
        ArrayList<Item> drop = new ArrayList<>();
        for(DropInfo info : this.dropTable) {
            if(Utils.rand.nextDouble() < info.chance) {
                drop.add(new Item(info.id, info.minQty == info.maxQty ? info.maxQty : Utils.getRandomInRange(info.minQty, info.maxQty)));
            }
        }

        return drop;
    }
}
