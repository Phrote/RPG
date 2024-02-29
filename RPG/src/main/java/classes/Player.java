/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

import interfaces.InputHandler;
import main.Game;
import utils.Utils;
import item.StorageComponent;


public class Player implements InputHandler {

    public String name;
    public LinkedHashMap<String, Stat> stats = new LinkedHashMap<>();
    public Gear gear = new Gear();
    public StorageComponent inventory = new StorageComponent(10);
    private static final Pattern namePattern = Pattern.compile("[a-zA-Z0-9]+", Pattern.CASE_INSENSITIVE);

    public static String[] commands = {"show self", "wear", "equip", "unequip", "remove", "set skill"};
    public static String[] infos = {"player_name"};

    public Player() {
        for (String s : Utils.playerSkills) {
            this.stats.put(s.toLowerCase(), new Stat(s));
        }
    }

    @Override
    public String toString() {
        String result = this.name + "\n------------\n";
        for (var stat : this.stats.entrySet()) {
            result = result.concat(stat.getValue().toString() + "\n");
        }
        return result;
    }

    @Override
    public String isHandleInput(String input, String info) {
        String prefix = Utils.getBestPrefix(this.commands, input);
        if(prefix != null) {
            for(String s : this.commands) {
                if(Utils.hammingClose(s, prefix)) {
                    return input;
                }
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
        String prefix = Utils.getBestPrefix(this.commands, input);
        String item = "";
        if(prefix != null) {
            try {
                switch(prefix) {
                    case "show self":
                        Game.gui.appendToOutputArea(Game.player.toString());
                        break;
                    case "wear":
                    case "equip":
                        item = input.replace(prefix + " ", "");
                        Game.player.gear.equip(item);
                        break;
                    case "unequip":
                    case "remove":
                        item = input.replace(prefix + " ", "");
                        Game.player.gear.unEquip(item);
                        break;
                    case "set skill":
                        input = input.replace(prefix + " ", "");
                        String words[] = input.split(" ");
                        Stat stat = Game.player.stats.get(words[0]);
                        stat.addXp(Integer.parseInt(words[1]));
                        Game.player.stats.put(words[0], stat);
                        System.out.println(Game.player.toString());
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(!info.isEmpty()) {
            switch(info) {
                case "player_name":
                    if (!namePattern.matcher(input).find()) {
                        Game.gui.appendToOutputArea("Name contains invalid characters.");
                    } else {
                        this.name = input;
                        Utils.askGeneralQuestion();
                        Game.gui.updateGearGUI();
                    }
                    break;

            }
        }
    }
}
