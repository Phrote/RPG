package interfaces;

import utils.Pair;

import java.util.Map;

public interface InputHandler {
    public Object isHandleInput(String input, String info);
    public void handleInput(String input, String info, Object data);

    public default String[] getCommands() {
        return new String[] {};
    }

    public default Pair<String, Integer> completeCommand(String input) {
        return new Pair<>(null, -1);
    }
}
