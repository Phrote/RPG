package utils;

import classes.npc.NPC;
import main.Game;

import java.util.Timer;
import java.util.TimerTask;

public class Combat {
    static NPC npc;
    static class playerCombatTimer extends TimerTask {

        @Override
        public void run() {
            int incomingDmg = (int)(Game.player.gear.calcFlatDmg() - npc.block - Math.floor(npc.skills.get("Defence")*0.3141));
            if(incomingDmg < 1) {
                Game.gui.appendToOutputArea(npc.name + "blocks " + npc.damage + " damage.");
            } else {
                npc.skills.put("Hitpoints", (npc.skills.get("Hitpoints") - incomingDmg));
                Game.gui.appendToOutputArea("You attack " + npc.name + " for " + incomingDmg + " damage.");
            }
            if(npc.skills.get("Hitpoints") < 1) {
                Game.gui.appendToOutputArea("You killed " + npc.name + ".");
                npcTimer.cancel();
                playerTimer.cancel();
            }
        }
    }
    static class npcCombatTimer extends TimerTask {

        @Override
        public void run() {
            int incomingDmg = (int) (npc.damage - Game.player.gear.calcFlatBlock() - Math.floor(Game.player.stats.get("Defence").level*0.3141));
            if(incomingDmg < 1) {
                Game.gui.appendToOutputArea("You block " + npc.damage + "damage");
            } else {
                Game.player.hp -= incomingDmg;
                Game.gui.appendToOutputArea(npc.name + " attacks you for " + incomingDmg + " damage.");
            }
            if(Game.player.hp < 1) {
                Game.gui.appendToOutputArea("You are DEAD!");
                npcTimer.cancel();
                playerTimer.cancel();
            }
        }
    }
    static Timer timer = new Timer();
    static playerCombatTimer playerTimer = new playerCombatTimer();
    static npcCombatTimer npcTimer = new npcCombatTimer();

    public static void fight(NPC npcInput) {
        npc = npcInput;
        System.out.println(Game.player.gear.calcFlatBlock());
        System.out.println(Game.player.stats.get("Defence").level*0.3141);
        timer.schedule(playerTimer, 0,(int) (Game.player.gear.getAttackSpeed() * 1000));
        timer.schedule(npcTimer, 0,(int) (npc.attackSpeed * 1000));


    }
    
}
