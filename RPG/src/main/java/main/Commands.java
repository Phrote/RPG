package main;

import classes.Player;
import com.google.gson.GsonBuilder;
import interfaces.InputHandler;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

import com.google.gson.Gson;
import utils.Utils;

/**
 *
 * @author ecsidav
 */
public class Commands implements InputHandler {

    public ArrayList<String> commands = new ArrayList<>(
            Arrays.asList("new game", "load game", "save", "exit", "quit"));

    @Override
    public Object isHandleInput(String input, String info) {
        for(String s : this.commands) {
            if(Utils.hammingClose(s, input)) {
                return s;
            }
        }
        return null;
    }

    @Override
    public void handleInput(String input, String info, Object obj) {
        String answer = (String)obj;
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
    }

    public static void saveGame() {
        Game.gui.appendToOutputArea("\n\nSaving...");
        try {
            FileWriter myWriter = new FileWriter(new File("save","player.json"));
            myWriter.write(new GsonBuilder().setPrettyPrinting().create().toJson(Game.player, Player.class));

            myWriter.close();
            Game.gui.appendToOutputArea("Saving was successful!");
            Utils.askGeneralQuestion();

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    public static void loadGame() {
        try {
            Game.player = new Gson().fromJson(Files.readString(Paths.get("save/player.json")), Player.class);
            Utils.inputHandlers.add(Game.player);
            Game.gui.clearOutputArea();
            Game.gui.appendToOutputArea("Loading was successful!");
            Game.gui.updateGearGUI();
            Game.gui.updateInventoryGUI();
            Utils.askGeneralQuestion();

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void newGame() {
        String question = "Enter Character Name:";
        Game.player = new Player();
        System.out.println(Game.player);
        Utils.inputHandlers.add(Game.player);
        System.out.println(Game.player);
        Utils.askQuestion(question, Game.player, "player_name");
    }

}
