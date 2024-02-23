package main;

import classes.Player;

public class Game {

    public static GUI gui;
    public static Player player;

    public static final Commands commands = new Commands();

    public static void main(String[] args) {
        gui = new GUI();
        Utils.showMainMenu();
    }


}
