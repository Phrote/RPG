/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import classes.Player;
import interfaces.QuestionHandler;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.regex.Pattern;

import com.google.gson.Gson;

/**
 *
 * @author ecsidav
 */
public class Commands implements QuestionHandler {

    public void handleAnswer(String answer, String info) {
        switch(answer)
        {
            case "1":
                if(Game.player == null) {
                    Game.gui.appendToOutputArea("There is no saved game. Please start a New Game.");
                    break;
                }
                Utils.questionHandler = null;
                break;
            case "2":
                Utils.questionHandler = null;
                Commands.newGame();
                break;
            case "3":
                Utils.questionHandler = null;
                Commands.loadGame();
                System.out.println(Game.player.toString());
                break;
            case "5":
            case "exit":
            case "quit":
                System.exit(0);
                break;
            case "save":
                saveGame();
                Utils.questionHandler = null;
                break;
            case "load":
                loadGame();
                Utils.questionHandler = null;
                break;
        }
    }

    public static void showMainMenu() {
        Utils.askQuestion(Menus.mainMenuText, Game.commands);
    }

    public static void saveGame() {
        Game.gui.appendToOutputArea("\n\nSaving...");
        try {
            FileWriter myWriter = new FileWriter("player.json");
            myWriter.write(new Gson().toJson(Game.player));

            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        Game.gui.appendToOutputArea("Saving was successful!");
    }

    public static void loadGame() {
        try {
            Game.player = new Gson().fromJson(Files.readString(Paths.get("player.json")), Player.class);
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void newGame() {
        String question = "Enter Character Name:";
        Game.player = new Player();
        Utils.askQuestion(question, Game.player, "name");
    }

}
