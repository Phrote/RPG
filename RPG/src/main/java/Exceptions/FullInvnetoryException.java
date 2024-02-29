package Exceptions;

import main.Game;

public class FullInvnetoryException extends Exception {
    public FullInvnetoryException() {
//        super("Inventory is full!");
        Game.gui.appendToOutputArea("Inventory is full!");
    }
}
