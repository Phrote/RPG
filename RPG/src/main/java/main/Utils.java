package main;

import interfaces.QuestionHandler;


public class Utils {


    public static QuestionHandler questionHandler;
    public static void askQuestion(String question, QuestionHandler handler) {
        Game.gui.setOutputArea(question);
        questionHandler = handler;
    }
}
