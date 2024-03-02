package utils;

import java.util.HashMap;

public class CommandTree {
    HashMap<String, CommandTree> tree;

    public CommandTree() {
        tree = new HashMap<>();
    }

    public CommandTree leaf(String cmd) {
        tree.put(cmd, null);
        return this;
    }

    public CommandTree leafs(String[] cmds) {
        for (String v : cmds) {
            tree.put(v, null);
        }
        return this;
    }

    public CommandTree branch(String cmd) {
        tree.put(cmd, new CommandTree());
        return tree.get(cmd);
    }
    private String getBest(String word) {
        for (var entry : tree.entrySet()) {
            if(entry.getKey().startsWith(word)) {
                return entry.getKey();
            }
        }
        return null;
    }
    public String complete(String input) {
        String[] t = input.split(" ");
        CommandTree next;
        int sep = input.indexOf(" ");
        String nextWord = sep == -1 ? input : input.substring(0, sep);
        String remInput = sep == -1 ? null : input.substring(sep+1);
        String cmd = getBest(nextWord);
        System.out.println(input + "(" + nextWord + ") --> " + cmd + ", rem: " + remInput);
        if(cmd == null) {
            return null;
        }

        if(remInput == null || remInput == "") {
            return cmd;
        }

        CommandTree nextTree = tree.get(cmd);

        if(nextTree == null) {
            return cmd;
        }

        String cmdNext = nextTree.complete(remInput);

        if(cmdNext == null) {
            return cmd;
        }

        return cmd + " " + cmdNext;
    }
}