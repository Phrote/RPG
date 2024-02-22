package classes;

import main.Utils;

import java.util.ArrayList;

public class NPC {
    public String name;
    public ArrayList<Stat> skills;

    public NPC(String name) {
        this.name = name;
        this.skills =  new ArrayList<>();
        for(String skill : Utils.npcSkills) {
            this.skills.add(new Stat(skill));
        }
    }

    public NPC(String name, ArrayList<Stat> skills) {
        this.name = name;
        this.skills = skills;
    }

}
