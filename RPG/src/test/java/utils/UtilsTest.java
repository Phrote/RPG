package utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UtilsTest {
    @Test
    void testCommandProcessor() {
        String command = "deposita 1 Bow of chaos";
        CommandProcessor cmdProc = new CommandProcessor(command);
        String best = cmdProc.popBestPrefix(new String[]{"deposit"}, 1);
        Assertions.assertEquals("deposit" , best);
        Assertions.assertEquals("1", cmdProc.pop());
        Assertions.assertEquals("Bow of chaos", cmdProc.popRest());
    }
}