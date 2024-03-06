package classes.npc.villager;

import classes.npc.DropTable;
import classes.npc.NPC;
import utils.PairList;

import java.util.HashMap;
import java.util.HashSet;

public class VillagerInfo extends NPC {

    public String description;
    public PairList chatList;


    public VillagerInfo(String name, HashMap<String, Integer> stats, DropTable droptable, String description, int flatBlock, int flatDmg, double attackSpeed, PairList chatList) {
        super();
        this.name = name;
        this.skills = stats;
        this.dropTable = droptable;
        this.description = description;
        this.block = flatBlock;
        this.damage = flatDmg;
        this.attackSpeed = attackSpeed;
        this.tags = new HashSet<>();
        this.chatList = chatList;
    }
}
