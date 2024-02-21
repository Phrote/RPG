package main;

import interfaces.InputHandler;

import java.util.ArrayList;
import java.util.Arrays;


public class Utils {

    public static InputHandler inputHandler;
    public static String questionInfo;

    public static final String[] playerSkills = {"Hitpoints", "Attack", "Strength", "Defence", "Magic"};

    public static ArrayList<InputHandler> inputHandlers = new ArrayList<>(
            Arrays.asList(Game.commands));

    public static void askQuestion(String question, InputHandler handler) {
        askQuestion(question, handler, null);
    }

    public static void askQuestion(String question, InputHandler handler, String info) {
        Game.gui.setOutputArea(question);
        inputHandler = handler;
        questionInfo = info;
    }

    public static void findInputHandler(String input, String info) {
        for(InputHandler handler : inputHandlers) {
            if(handler.isHandleInput(input, info)) {
                handler.handleInput(input, info);
                return;
            }
        }
    }

    public static void showMainMenu() {
        askQuestion(Text.mainMenuText, Game.commands);
    }

    public static void askGeneralQuestion() {
        askQuestion(Text.generalQuestionText, Game.commands);
        Game.gui.appendToOutputArea("asdassdasdasdasdadad");
        Game.gui.appendToOutputArea("asdassdasdasdasdadad");
        Game.gui.appendToOutputArea("asdassdasdasdasdadad");
        Game.gui.appendToOutputArea("asdassdasdasdasdadad");
        Game.gui.appendToOutputArea("asdassdasdasdasdadad");
        Game.gui.appendToOutputArea("asdassdasdasdasdadadaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        Game.gui.appendToOutputArea("asdassdasdasdasdadad");
        Game.gui.appendToOutputArea("asdassdasdasdasdadad");
        Game.gui.appendToOutputArea("asdassdasdasdasdadad");
        Game.gui.appendToOutputArea("asdassdasdasdasdadad");
        Game.gui.appendToOutputArea("asdassdasdasdasdadad");
        Game.gui.appendToOutputArea("asdassdasdasdasdadad");
        Game.gui.appendToOutputArea("asdassdasdasdasdadad");
        Game.gui.appendToOutputArea("asdassdasdasdasdadad");
        Game.gui.appendToOutputArea("asdassdasdasdasdadad");
        Game.gui.appendToOutputArea("asdassdasdasdasdadad");
        Game.gui.appendToOutputArea("asdassdasdasdasdadad");
        Game.gui.appendToOutputArea("asdassdasdasdasdadad");
        Game.gui.appendToOutputArea("asdassdasdasdasdadad");
        Game.gui.appendToOutputArea("asdassdasdasdasdadad");
        Game.gui.appendToOutputArea("asdassdasdasdasdadad");
        Game.gui.appendToOutputArea("asdassdasdasdasdadad");
        Game.gui.appendToOutputArea("asdassdasdasdasdadad");
        Game.gui.appendToOutputArea("asdassdasdasdasdadad");
        Game.gui.appendToOutputArea("asdassdasdasdasdadad");
        Game.gui.appendToOutputArea("asdassdasdasdasdadad");
        Game.gui.appendToOutputArea("asdassdasdasdasdadad");
        Game.gui.appendToOutputArea("asdassdasdasdasdadad");
        Game.gui.appendToOutputArea("asdassdasdasdasdadad");
        Game.gui.appendToOutputArea("asdassdasdasdasdadad");
        Game.gui.appendToOutputArea("asdassdasdasdasdadad");
        Game.gui.appendToOutputArea("asdassdasdasdasdadad");
    }
}
