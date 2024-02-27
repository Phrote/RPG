package main;

import Exceptions.NoItemException;
import classes.Player;

import classes.npc.mob.MobDatabase;
import classes.npc.villager.VillagerDatabase;
import storage.Item;
import storage.ItemDatabase;
import utils.Utils;

public class Game {

    public static GUI gui;
    public static Player player;

    public static final Commands commands = new Commands();

    public static void main(String[] args) {
        gui = new GUI();
        Utils.showMainMenu();
//        Commands.loadGame();
//        player.inventory.place(new Item("apple", 5));
//        player.inventory.place(new Item("bow", 1));
//        player.inventory.place(new Item("bone", 10));
//        player.inventory.place(new Item("knife", 30));

//        try {
//            player.gear.wearGear("bow", "weapon");
//        } catch (NoItemException e) {
//            e.printStackTrace();
//        }
    }


}
