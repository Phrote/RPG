package utils;

import classes.Stat;
import interfaces.InputHandler;
import main.GUI;
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

    public static ArrayList<InputHandler> inputHandlers = new ArrayList<>();

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
        for(InputHandler handler : inputHandlers) {
            Object obj = handler.isHandleInput(input, info);
            if(obj != null) {
                handler.handleInput(input, info, obj);
                return;
            }
        }
    }

    public static String autoCompleteInput(String input) {
        Pair<String, Integer> best = null;
        for(InputHandler handler : inputHandlers) {
            Pair<String, Integer> ret = handler.completeCommand(input);
            if(ret == null || ret.key == null)
                continue;

            if(best == null || ret.value < best.value) {
                best = ret;
            }
        }

        if(best != null) {
            return best.key;
        }
        return null;
    }

    public static void listCommands() {
        for(InputHandler handler : inputHandlers) {
            for (String v : handler.getCommands()) {
                Game.gui.appendToOutputArea(v);
            }
        }
    }

    public static boolean hammingClose(String a, String b) {
        return hammingClose(a,b,1);
    }

    public static boolean hammingClose(String a, String b, int max) {
        return hammingDist(a,b) <= max;
    }

    public static int hammingDist(String a, String b) {
        int dist = Math.abs(a.length()-b.length());
        for(int i = 0;i<Math.min(a.length(),b.length());i++) {
            if(a.charAt(i) != b.charAt(i)) {
                dist++;
            }
        }
        return dist;
    }

    public static boolean hammingPrefix(String text, String prefix, int hamming) {
        int dist = Math.max(prefix.length() - text.length(), 0);
        if(dist > hamming)
            return false;
        for(int i = 0;i<Math.min(text.length(),prefix.length());i++) {
            if(text.charAt(i) != prefix.charAt(i)) {
                dist++;
            }
        }
        return dist <= hamming;
    }

    public static String getBestPrefix(String[] prefixes, String text) {
        return getBestPrefix(prefixes, text, 0);
    }

    public static String getBestPrefix(String[] prefixes, String text, int hamming) {
        String longestPrefix = null;
        for(String prefix : prefixes) {
            if(hammingPrefix(text, prefix, hamming)) {
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

    public static class CommandProcessor {
        public String getCommand() {
            return command;
        }

        String command;

        public CommandProcessor(String command) {
            this.command = command.trim();
        }

        public String popBestPrefix(String[] prefixes, int hamming) {
            String best = getBestPrefix(prefixes, command, hamming);
            if(best == null) {
                return null;
            }
            this.command = this.command.substring(best.length());
            int nextSpace = this.command.indexOf(" ");
            if(nextSpace != -1 && nextSpace >= 0 && nextSpace <= hamming) {
                this.command = this.command.substring(nextSpace+1);
            }
            return best;
        }

        public String popRest() {
            String rest = this.command;
            this.command = "";
            return rest;
        }

        public String[] pop(int n) {
            String t[] = this.command.split(" ");
            if(t.length >= n) {
                String[] ret = Arrays.copyOfRange(t, 0, n);
                this.command = this.command.substring(String.join(" ", ret).length());
                this.command = this.command.trim();
                return ret;
            }
            return null;
        }

        public String pop() {
            String[] t = pop(1);
            if(t.length == 1 && t[0].length() > 0) {
                return t[0];
            }
            return null;
        }

        public String get() {
            return command;
        }
    }
}
