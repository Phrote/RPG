package Exceptions;

import storage.ItemDatabase;

public class NoItemException extends Exception {
    public NoItemException(String itemId) {
        super("There is no " + ItemDatabase.getItemInfo(itemId).name + " in your inventory!");
    }
}
