/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import classes.Player;
import interfaces.InputHandler;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

import com.google.gson.Gson;
import jdk.jshell.execution.Util;

/**
 *
 * @author ecsidav
 */
public class Commands implements InputHandler {

    public ArrayList<String> commands = new ArrayList<>(
            Arrays.asList("new game", "load game", "save", "exit", "quit"));

    @Override
    public boolean isHandleInput(String input, String info) {
        return this.commands.contains(input);
    }

    @Override
    public void handleInput(String answer, String info) {
        switch(answer) {
            case "new game":
                newGame();
                break;
            case "save":
                saveGame();
                break;
            case "load game":
                loadGame();
                break;
            case "exit":
            case "quit":
                System.exit(0);
        }
        Utils.askGeneralQuestion();

    }

    public static void saveGame() {
        Game.gui.appendToOutputArea("\n\nSaving...");
        try {
            FileWriter myWriter = new FileWriter("player.json");
            myWriter.write(new Gson().toJson(Game.player, Player.class));

            myWriter.close();
            Game.gui.appendToOutputArea("Saving was successful!");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    public static void loadGame() {
        try {
            Game.player = new Gson().fromJson(Files.readString(Paths.get("player.json")), Player.class);
            Game.gui.appendToOutputArea("Loading was successful!");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void newGame() {
        String question = "Enter Character Name:";
        Game.player = new Player();
        Utils.inputHandlers.add(Game.player);
        Utils.askQuestion(question, Game.player, "player_name");
    }

}
