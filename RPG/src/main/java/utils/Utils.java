package utils;

import classes.Stat;
import interfaces.InputHandler;
import main.Game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;


public class Utils {

    public static Random rand = new Random();
    public static InputHandler inputHandler;
    public static String questionInfo;

    public static final HashMap<String, Stat> skills = new HashMap<>();
    public static int xpLevels[];

    public static ArrayList<InputHandler> inputHandlers = new ArrayList<>(
            Arrays.asList(Game.commands));

    static {
        fillSkills();
    }

    public static void fillSkills() {
        skills.put("Hitpoints", new Stat());
        skills.put("Attack", new Stat());
        skills.put("Strength", new Stat());
        skills.put("Defence", new Stat());
        skills.put("Magic", new Stat());
        skills.put("Ranged", new Stat());

        xpLevels = new int[100];
        xpLevels[0] = 100;
        for(int i = 1; i < 100; i++){
            xpLevels[i] = (int) Math.floor(xpLevels[i-1] * 1.12356);

        }
    }

    public static void askQuestion(String question, InputHandler handler) {
        askQuestion(question, handler, "");
    }

    public static void askQuestion(String question, InputHandler handler, String info) {
        Game.gui.appendToOutputArea(question);
        inputHandler = handler;
        questionInfo = info;
    }

    public static void findInputHandler(String input, String info) {
        String str = "";
        for(InputHandler handler : inputHandlers) {
            str = handler.isHandleInput(input, info);
            if(!str.isEmpty()) {
                handler.handleInput(input, str);
                return;
            }
        }
    }

    public static boolean hammingClose(String a, String b) {
        return hammingClose(a,b,1);
    }

    public static boolean hammingClose(String a, String b, int max) {
        int dist = Math.abs(a.length()-b.length());
        if(dist > max)
            return false;
        for(int i = 0;i<Math.min(a.length(),b.length());i++) {
            if(a.charAt(i) != b.charAt(i)) {
                dist++;
            }
        }
        return dist <= max;
    }

    public static String getBestPrefix(String[] prefixes, String text) {
        String longestPrefix = null;
        for(String prefix : prefixes) {
//            System.out.println(prefix);
//            System.out.println(text.startsWith(prefix));
            if(text.startsWith(prefix)) {
                if(longestPrefix == null || prefix.length() > longestPrefix.length()) {
                    longestPrefix = prefix;
                }
            }
        }
        return longestPrefix;
    }
    public static void showMainMenu() {
        askQuestion(Text.mainMenuText, Game.commands);
    }

    public static void askGeneralQuestion() {
        askQuestion(Text.generalQuestionText, Game.commands);
    }

    public static int getRandomInRange(int min, int max)
    {
        return rand.nextInt(max-min) + min;
    }
}
