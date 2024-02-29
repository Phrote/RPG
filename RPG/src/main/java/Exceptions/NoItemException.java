package Exceptions;

import item.ItemDatabase;
import main.Game;

public class NoItemException extends Exception {
    public NoItemException(String itemId) {
        Game.gui.appendToOutputArea("There is no " + ItemDatabase.getItemInfo(itemId).name + " in your inventory!");
//        super("There is no " + ItemDatabase.getItemInfo(itemId).name + " in your inventory!");
    }
}
