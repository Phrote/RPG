package main;

import interfaces.QuestionHandler;

/**
 *
 * @author ecsidav
 */
public class Menus implements QuestionHandler {
    private static final Menus menus = new Menus();
    public static void mainMenu()
    {
        String menuText = "1. New Game\n2. Load Game\n5. Exit";
        Utils.askQuestion(menuText, menus);
    }

    public void handleAnswer(String answer) {
        boolean chosen = false;
        switch(answer)
        {
            case "1":
                System.out.println("New Game");
                Commands.newGame();
                chosen = true;
                break;
            case "2":
                System.out.println("Load Game");
                Commands.loadGame();
                System.out.println(Game.player.toString());
                chosen = true;
                break;
            case "5":
            case "exit":
            case "quit":
                System.exit(0);
                break;
        }
        if(chosen) {
            Utils.questionHandler = null;
        }
    }
}
