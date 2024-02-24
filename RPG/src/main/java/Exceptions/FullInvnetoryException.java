package Exceptions;

public class FullInvnetoryException extends Exception {
    public FullInvnetoryException() {
        super("Inventory is full!");
    }
}
