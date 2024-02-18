/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import classes.Player;
import interfaces.QuestionHandler;

import java.util.Scanner;
import java.util.regex.Pattern;

/**
 *
 * @author ecsidav
 */
public class Commands implements QuestionHandler {

    private static final Commands commands = new Commands();
    private static final Pattern namePattern = Pattern.compile("[a-zA-Z0-9]+", Pattern.CASE_INSENSITIVE);

    public void handleAnswer(String answer) {
        if (!namePattern.matcher(answer).find()) {
            Game.gui.appendToOutputArea("Name contains invalid characters.");

        } else {
            Game.player = new Player(answer);
            return;
        }
//        switch(answer) {
//            case "save":
//                saveGame();
//                break;
//
//        }
    }
    public static void saveGame() {
        System.out.println("Saving...");
        Game.player.save();
        System.out.println("Saving was Successful!");
    }

    public static void loadGame() {
        System.out.println("Loading...");
        Player.readPlayer();
        System.out.println("Loading was Successful!");
    }

    public static void newGame() {
        String question = "Enter Character Name:";
        Utils.askQuestion(question, commands);
    }

}
