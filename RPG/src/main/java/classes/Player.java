/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

import interfaces.InputHandler;
import main.Game;
import main.Text;
import main.Utils;


public class Player implements InputHandler {

    public String name;
    public ArrayList<Stat> stats = new ArrayList<>();
    private static final Pattern namePattern = Pattern.compile("[a-zA-Z0-9]+", Pattern.CASE_INSENSITIVE);

    public ArrayList<String> infos = new ArrayList<>(
            Arrays.asList("player_name"));

    public Player() {
        for (String s : Utils.playerSkills) {
            this.stats.add(new Stat(s));
        }
    }

    @Override
    public String toString() {
        String result = "\n" + this.name + "\n------------\n";
        for (Stat stat : this.stats) {
            result = result.concat(stat.toString() + "\n");
        }
        return result;
    }

    @Override
    public boolean isHandleInput(String input, String info) {
        return infos.contains(info);
    }

    @Override
    public void handleInput(String answer, String info) {
        switch(info) {
            case "player_name":
                if (!namePattern.matcher(answer).find()) {
                    Game.gui.appendToOutputArea("Name contains invalid characters.");
                } else {
                    this.name = answer;
                    Utils.askGeneralQuestion();
                }
                break;

        }
    }
}
