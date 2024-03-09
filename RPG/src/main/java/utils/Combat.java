package utils;

import classes.npc.NPC;
import main.Game;

import java.util.Timer;
import java.util.TimerTask;

public class Combat {
    static NPC npc;
    static int hp;
    static class playerCombatTimer extends TimerTask {

        @Override
        public void run() {
            int incomingDmg = (int)(Game.player.gear.calcFlatDmg() - npc.block - Math.floor(npc.skills.get("Defence")*0.3141));
            if(incomingDmg < 1) {
                Game.gui.appendToOutputArea(npc.name + "blocks " + npc.damage + " damage.");
            } else {
                hp -= incomingDmg;
                Game.gui.appendToOutputArea("You attack " + npc.name + " for " + incomingDmg + " damage.");
            }
            if(hp < 1) {
                Game.gui.appendToOutputArea("You killed " + npc.name + ".");
                npcTimer.cancel();
                playerTimer.cancel();
                Encounter.combatFinish();
            }
        }
    }
    static class npcCombatTimer extends TimerTask {

        @Override
        public void run() {
            int incomingDmg = (int) (npc.damage - Game.player.gear.calcFlatBlock() - Math.floor(Game.player.stats.get("Defence").level*0.3141));
            if(incomingDmg < 1) {
                Game.gui.appendToOutputArea("You block " + npc.damage + " damage");
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
    static Timer timer = new Timer();;
    static playerCombatTimer playerTimer;
    static npcCombatTimer npcTimer;

    public static void fight(NPC npcInput) {
        playerTimer = new playerCombatTimer();
        npcTimer = new npcCombatTimer();
        npc = npcInput;
        hp = npc.skills.get("Hitpoints");
        timer.schedule(playerTimer, 1,(int) (Game.player.gear.getAttackSpeed() * 1000));
        timer.schedule(npcTimer, 1,(int) (npc.attackSpeed * 1000));


    }
    
}
