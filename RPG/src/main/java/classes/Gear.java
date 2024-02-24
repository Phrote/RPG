package classes;

import Exceptions.FullInvnetoryException;
import Exceptions.NoItemException;
import main.Game;
import storage.ItemDatabase;

public class Gear {
    public String head = "";
    public String shoulder = "";
    public String chest = "";
    public String legs = "";
    public String feet = "";
    public String gloves = "";
    public String neck = "";
    public String ring1 = "";
    public String ring2 = "";
    public String cape = "";
    public String weapon = "";
    public String shield = "";
    public String ammo = "";

    public void wearGear(String item, String type) throws NoItemException {
        if(Game.player.inventory.count(item) < 1) {
            throw new NoItemException(item);
        }
        switch(type) {
            case "head":
                this.head = item;
                break;
            case "shoulder":
                this.shoulder = item;
                break;
            case "chest":
                this.chest = item;
                break;
            case "legs":
                this.legs = item;
                break;
            case "feet":
                this.feet = item;
                break;
            case "gloves":
                this.gloves = item;
                break;
            case "neck":
                this.neck = item;
                break;
            case "ring1":
                this.ring1 = item;
                break;
            case "ring2":
                this.ring2 = item;
                break;
            case "cape":
                this.cape = item;
                break;
            case "weapon":
                this.weapon = item;
                break;
            case "shield":
                this.shield = item;
                break;
            case "ammo":
                this.ammo = item;
                break;

        }
        Game.gui.updateGearGUI();
    }

    public void removeGear(String type) throws FullInvnetoryException {
        if(Game.player.inventory.getFilledSlots() == Game.player.inventory.size) {
            throw new FullInvnetoryException();
        }
        switch(type) {
            case "head":
                this.head = "";
                break;
            case "shoulder":
                this.shoulder = "";
                break;
            case "chest":
                this.chest = "";
                break;
            case "legs":
                this.legs = "";
                break;
            case "feet":
                this.feet = "";
                break;
            case "gloves":
                this.gloves = "";
                break;
            case "neck":
                this.neck = "";
                break;
            case "ring1":
                this.ring1 = "";
                break;
            case "ring2":
                this.ring2 = "";
                break;
            case "cape":
                this.cape = "";
                break;
            case "weapon":
                this.weapon = "";
                break;
            case "shield":
                this.shield = "";
                break;
            case "ammo":
                this.ammo = "";
                break;

        }
        Game.gui.updateGearGUI();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Weapon: " + (this.weapon.equals("") ? "" : ItemDatabase.getItemInfo(this.weapon).name) + "\n");
        sb.append("Shield: " + (this.shield.equals("") ? "" : ItemDatabase.getItemInfo(this.shield).name) + "\n");
        sb.append("Ammo: " + (this.ammo.equals("") ? "" : ItemDatabase.getItemInfo(this.ammo).name) +"\n-----------------------------\n");
        sb.append("Head: " + (this.head.equals("") ? "" : ItemDatabase.getItemInfo(this.head).name) + "\n");
        sb.append("Shoulder: " + (this.shoulder.equals("") ? "" : ItemDatabase.getItemInfo(this.shoulder).name) + "\n");
        sb.append("Chest: " + (this.chest.equals("") ? "" : ItemDatabase.getItemInfo(this.chest).name) +  "\n");
        sb.append("Legs: " + (this.legs.equals("") ? "" : ItemDatabase.getItemInfo(this.legs).name) + "\n");
        sb.append("Feet: " + (this.feet.equals("") ? "" : ItemDatabase.getItemInfo(this.feet).name) + "\n");
        sb.append("Gloves: " + (this.gloves.equals("") ? "" : ItemDatabase.getItemInfo(this.gloves).name) + "\n");
        sb.append("Neck: " + (this.neck.equals("") ? "" : ItemDatabase.getItemInfo(this.neck).name) + "\n");
        sb.append("Ring 1: " + (this.ring1.equals("") ? "" : ItemDatabase.getItemInfo(this.ring1).name) +  "\n");
        sb.append("Ring 2: " + (this.ring2.equals("") ? "" : ItemDatabase.getItemInfo(this.ring2).name) +  "\n");
        sb.append("Cape: " + (this.cape.equals("") ? "" : ItemDatabase.getItemInfo(this.cape).name) + "\n");

        return sb.toString();
    }
}
