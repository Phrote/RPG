/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import Classes.Player;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 *
 * @author ecsidav
 */
public class Commands {

    private static Scanner sc = new Scanner(System.in);
    private static final Pattern namePattern = Pattern.compile("[a-zA-Z0-9]+", Pattern.CASE_INSENSITIVE);

    public static void saveGame() {
        System.out.println("Saving...");
        Game.player.save();
        System.out.println("Saving was Successful!");
    }

    public static void loadGame() {
        System.out.println("Loading...");

        System.out.println("Loading was Successful!");
    }

    public static void newGame() {

        while (true) {
            System.out.println("Enter Character Name: ");
            String name = sc.nextLine();
            if (!namePattern.matcher(name).find()) {
                System.out.println("Name contains invalid characters.");
            } else {
                Game.player = new Player(name);
                return;
            }
        }
    }

}
