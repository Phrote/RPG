package main;

import Exceptions.FullInvnetoryException;
import Exceptions.LvlReqNotMetexception;
import Exceptions.NoAvailableSlotsException;
import Exceptions.NoItemException;
import classes.Player;

import item.Item;
import utils.Utils;

public class Game {

    public static GUI gui;
    public static Player player;

    public static final Commands commands = new Commands();

    public static void main(String[] args) {
        gui = new GUI();
        Utils.showMainMenu();
        Commands.loadGame();

        try {
            Game.player.gear.equip("Bronze helm");
            Game.player.gear.equip("Bronze platebody");
            Game.player.gear.equip("Bronze shoulder");
            Game.player.gear.equip("Bronze plateleg");
            Game.player.gear.equip("Bronze sword");
            Game.player.gear.equip("Bronze boots");
            Game.player.gear.equip("Bronze gloves");
            Game.player.gear.equip("Fire cape");
            Game.player.gear.equip("Ring of Soup");
            Game.player.gear.equip("Ring of Bread");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            gui.appendToOutputArea(e.getLocalizedMessage());
        }


    }


}
