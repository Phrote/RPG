package classes.npc.mob;

import classes.Stat;
import classes.npc.NPC;
import classes.npc.DropTable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class MobInfo extends NPC {

    public int maxStack;

    public MobInfo(String name, HashMap<String, Integer> stats, DropTable droptable, int maxStack) {
        super();
        this.name = name;
        this.skills = stats;
        this.dropTable = droptable;
        this.maxStack = maxStack;
        this.tags = new HashSet<>();
    }

    @Override
    public String toString() {
        String result = this.name + "\n";
        for (var stat : this.skills.entrySet()) {
            result = result.concat(stat.getKey() + ": " + stat.getValue() + "\n");
        }

        result = result.concat(this.dropTable.dropTable.toString());
        result = result.concat(" " + this.maxStack);
        return result;
    }
}