package classes.npc.mob;

import classes.Stat;
import classes.npc.NPC;
import classes.npc.DropTable;

import java.util.ArrayList;
import java.util.HashSet;

public class MobInfo extends NPC {

    public int maxStack;

    public MobInfo(String name, ArrayList<Stat> stats, DropTable droptable, int maxStack) {
        super();
        this.name = name;
        this.skills = stats;
        this.dropTable = droptable;
        this.maxStack = maxStack;
        this.tags = new HashSet<>();
    }

    @Override
    public String toString() {
        String result = this.name + ", ";
        for (Stat stat : this.skills) {
            result = result.concat(stat.toString() + " ");
        }

        result = result.concat(this.dropTable.dropTable.toString());
        result = result.concat(" " + this.maxStack);
        return result;
    }
}