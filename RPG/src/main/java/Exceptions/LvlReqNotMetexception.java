package Exceptions;

import main.Game;

public class LvlReqNotMetexception extends Exception {
    public LvlReqNotMetexception(String skill) {
//        super(skill + " requirement is not met!");
        Game.gui.appendToOutputArea(skill + " requirement is not met!");
    }
}
