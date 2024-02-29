package Exceptions;

import item.ItemDatabase;
import main.Game;

public class NoAvailableSlotsException extends Exception {

    public NoAvailableSlotsException(String type) {
        super("There are no available " + type + " slots!");
        Game.gui.appendToOutputArea("There are no available " + type + " slots!");
    }
}
