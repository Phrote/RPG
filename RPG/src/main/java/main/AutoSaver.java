package main;

import interfaces.InputHandler;
import utils.Utils;

import java.util.Timer;
import java.util.TimerTask;

public class AutoSaver implements InputHandler {
    int interval = 5;
    Timer timer = new Timer();

    boolean autoSaving = false;
    AutoSaveTimer timerTask;

    class AutoSaveTimer extends TimerTask {

        @Override
        public void run() {
            Commands.saveGame();
        }
    }

    @Override
    public Object isHandleInput(String input, String info) {
        return Utils.getBestPrefix(new String[]{"autosave", "autosaving"}, input,1);
    }

    @Override
    public void handleInput(String input, String info, Object obj) {
        Utils.CommandProcessor cmdProc = new Utils.CommandProcessor(input);
        cmdProc.popBestPrefix(new String[]{"autosave", "autosaving"},1);
        String cmd = cmdProc.popBestPrefix(new String[]{"on", "off", "interval"}, 1);
        if(cmd == null) {
            return;
        }

        switch (cmd) {
            case "on":
                activate();
                break;
            case "off":
                deactivate();
                break;
            case "interval":
                try {
                    int mins = Integer.parseInt(cmdProc.pop());
                    setInterval(mins);
                } catch (NumberFormatException e) {
                    e.printStackTrace(System.err);
                    return;
                }
                break;
        }

    }

    public void activate() {
        timerTask = new AutoSaveTimer();
        timer.schedule(timerTask, interval*60*1000, interval*60*1000);
        autoSaving = true;
    }
    public void deactivate() {
        timerTask.cancel();
        timerTask = null;
        autoSaving = false;
    }

    public void setInterval(int mins) {
        interval = mins;
        if (autoSaving) {
            deactivate();
            activate();
        }
    }
}
