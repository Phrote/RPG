/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classes;

import java.util.ArrayList;
import java.util.regex.Pattern;

import interfaces.QuestionHandler;
import main.Game;
import main.Utils;


public class Player implements QuestionHandler {

    public String name;
    public ArrayList<Stat> stats = new ArrayList<>();
    private static final Pattern namePattern = Pattern.compile("[a-zA-Z0-9]+", Pattern.CASE_INSENSITIVE);


    public Player() {
        for (String s : Utils.playerSkills) {
            this.stats.add(new Stat(s));
        }
    }

    public Player(String name) {
        this.name = name;
        for (String s : Utils.playerSkills) {
            this.stats.add(new Stat(s));
        }
    }

    public Player(String name, ArrayList<Stat> stats) {
        this.name = name;
        this.stats = stats;
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
    public void handleAnswer(String answer, String info) {
        switch(info) {
            case "name":
                if (!namePattern.matcher(answer).find()) {
                    Game.gui.appendToOutputArea("Name contains invalid characters.");
                } else {
                    this.name = answer;
                    Utils.questionHandler = null;
                }
                break;

        }
    }
}
