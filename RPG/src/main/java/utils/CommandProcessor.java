package utils;

import java.util.Arrays;

public class CommandProcessor {
    public String getCommand() {
        return command;
    }

    String command;

    public CommandProcessor(String command) {
        this.command = command.trim();
    }

    public String popBestPrefix(String[] prefixes, int hamming) {
        String best = Utils.getBestPrefix(prefixes, command, hamming);
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