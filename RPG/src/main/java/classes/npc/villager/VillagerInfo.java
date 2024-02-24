package classes.npc.villager;

import classes.Stat;
import classes.npc.DropTable;
import classes.npc.NPC;
import utils.PairList;

import java.util.ArrayList;
import java.util.HashSet;

public class VillagerInfo extends NPC {

    public String description;
    public PairList chatList;


    public VillagerInfo(String name, ArrayList<Stat> stats, DropTable droptable, String description, PairList chatList) {
        super();
        this.name = name;
        this.skills = stats;
        this.dropTable = droptable;
        this.description = description;
        this.tags = new HashSet<>();
        this.chatList = chatList;
    }
}
