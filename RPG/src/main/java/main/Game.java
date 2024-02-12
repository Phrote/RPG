/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package main;

import Classes.Player;
import java.util.Scanner;

/**
 *
 * @author ecsidav
 */
public class Game {

    public static Player player;

    public static void main(String[] args) {
        String input = "";
        Scanner sc = new Scanner(System.in);
        Menus.mainMenu();

        while (true) {
            input = sc.nextLine();
            switch (input) {
                case "menu":
                    Menus.mainMenu();
                    break;
                case "save":
                    Commands.saveGame();
                    break;
                case "exit":
                case "quit":
                    System.exit(0);
                    break;
            }
        }
    }
}
