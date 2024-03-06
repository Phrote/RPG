package main;

import Exceptions.FullInvnetoryException;
import Exceptions.LvlReqNotMetexception;
import Exceptions.NoAvailableSlotsException;
import Exceptions.NoItemException;
import classes.Player;

import classes.npc.mob.MobDatabase;
import item.Item;
import utils.Combat;
import utils.Utils;

public class Game {

    public static GUI gui;
    public static Player player;

    public static final Commands commands = new Commands();

    public static void main(String[] args) {
        setupInputHandlers();
        gui = new GUI();
        Utils.showMainMenu();
        Commands.loadGame();
        System.out.println(MobDatabase.getMobInfo("cow"));

//        Game.player.inventory.place(new Item("bronze helm",1));
//        Game.player.inventory.place(new Item("bronze platebody",1));
//        Game.player.inventory.place(new Item("bronze sword",1));

//        try {
//            Game.player.gear.equip("Bronze helm");
//            Game.player.gear.equip("Bronze platebody");
//            Game.player.gear.equip("Bronze shoulder");
//            Game.player.gear.equip("Bronze plateleg");
//            Game.player.gear.equip("Bronze sword");
//            Game.player.gear.equip("Bronze boots");
//            Game.player.gear.equip("Bronze gloves");
//            Game.player.gear.equip("Fire cape");
//            Game.player.gear.equip("Ring of Soup");
//            Game.player.gear.equip("Ring of Bread");
//        } catch (Exception e) {
//            gui.appendToOutputArea(e.getLocalizedMessage());
//        }

//        Combat.fight(MobDatabase.getMobInfo("cow"));

    }

    public static void setupInputHandlers() {
        Utils.inputHandlers.add(new AutoSaver());
        Utils.inputHandlers.add(commands);
    }



}
