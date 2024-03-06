package classes.npc;

import classes.Stat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class NPC {
    public String name;
    public HashMap<String, Integer> skills;
    public DropTable dropTable;
    public Set<String> tags;
    public int block;
    public int damage;
    public double attackSpeed;

}
