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

    public ArrayList<String> commands = new ArrayList<>(
            Arrays.asList("show self"));
    public ArrayList<String> infos = new ArrayList<>(
            Arrays.asList("player_name"));

    public Player() {
        for (String s : Utils.playerSkills) {
            this.stats.add(new Stat(s));
        }
    }

    @Override
    public String toString() {
        String result = this.name + "\n------------\n";
        for (Stat stat : this.stats) {
            result = result.concat(stat.toString() + "\n");
        }
        return result;
    }

    @Override
    public String isHandleInput(String input, String info) {
        System.out.println(input);
        for(String s : this.commands) {
            if(Utils.hammingClose(s, input)) {
                return s;
            }
        }
        for(String s : this.infos) {
            if(Utils.hammingClose(s, info)) {
                return s;
            }
        }
        return "";
    }

    @Override
    public void handleInput(String input, String info) {
        switch(input) {
            case "show self":
                Game.gui.appendToOutputArea(Game.player.toString());
                break;
        }
        if(!info.isEmpty()) {
            switch(info) {
                case "player_name":
                    if (!namePattern.matcher(input).find()) {
                        Game.gui.appendToOutputArea("Name contains invalid characters.");
                    } else {
                        this.name = input;
                        Utils.askGeneralQuestion();
                    }
                    break;

            }
        }
    }
}
