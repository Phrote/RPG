package utils;

import classes.npc.NPC;

import java.util.ArrayList;

public class Encounter {
    public static ArrayList<Pair<NPC, Integer>> mobs = new ArrayList<>();

    public Encounter(ArrayList<Pair<NPC, Integer>> mobs) {
        this.mobs = mobs;
    }

    public static void simulate() {
        Combat.fight(mobs.get(mobs.size() - 1).key);
    }

    public static void combatFinish(){
        mobs.get(mobs.size() - 1).value--;
        if(mobs.get(mobs.size() - 1).value > 0) {
            Combat.fight(mobs.get(mobs.size() - 1).key);
        } else {
            mobs.remove(mobs.size() - 1);
            if(mobs.size() > 0) {
                Combat.fight(mobs.get(mobs.size() - 1).key);
            } else {
                Combat.timer.cancel();
            }
        }

    }
}


