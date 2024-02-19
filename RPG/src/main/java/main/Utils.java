package main;

import interfaces.QuestionHandler;


public class Utils {

    public static QuestionHandler questionHandler;
    public static String questionInfo;

    public static final String[] playerSkills = {"Hitpoints", "Attack", "Strength", "Defence", "Magic"};

    public static void askQuestion(String question, QuestionHandler handler) {
        askQuestion(question, handler, null);
    }

    public static void askQuestion(String question, QuestionHandler handler, String info) {
        Game.gui.setOutputArea(question);
        questionHandler = handler;
        questionInfo = info;
    }
}
