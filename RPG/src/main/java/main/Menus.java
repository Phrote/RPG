/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.util.Scanner;
import java.util.regex.Pattern;

/**
 *
 * @author ecsidav
 */
public class Menus {
    private static Scanner sc = new Scanner(System.in);
    public static void mainMenu()
    {
      System.out.println("1. New Game");
      System.out.println("2. Load Game");
      System.out.println("\n5. Exit");

      switch(sc.nextLine())
      {
        case "1": 
            System.out.println("New Game");
            Commands.newGame();
            break;
        case "2":
            System.out.println("Load Game");
            Commands.loadGame();
            System.out.println(Game.player.toString());
            break;
        case "5":
        case "exit":
        case "quit":
            System.exit(0);
            break;
      }
      
    }
}
