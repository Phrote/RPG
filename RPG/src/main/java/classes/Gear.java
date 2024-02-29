package classes;

import Exceptions.FullInvnetoryException;
import Exceptions.LvlReqNotMetexception;
import Exceptions.NoAvailableSlotsException;
import Exceptions.NoItemException;
import item.*;
import main.Game;
import utils.Pair;


import java.util.LinkedHashMap;

public class Gear {
    private LinkedHashMap<String, String> gearList = new LinkedHashMap<>();
    private Pair ammunition;

    public Gear() {
        gearList.put("Weapon", "");
        gearList.put("Shield", "");
//        gearList.put("Ammunition", "");
        gearList.put("Helmet", "");
        gearList.put("Shoulder", "");
        gearList.put("Chest", "");
        gearList.put("Legs", "");
        gearList.put("Feet", "");
        gearList.put("Gloves", "");
        gearList.put("Neck", "");
        gearList.put("Ring1", "");
        gearList.put("Ring2", "");
        gearList.put("Cape", "");
        this.ammunition = new Pair();
    }

    public void equip(String itemName) throws NoItemException, NoAvailableSlotsException, LvlReqNotMetexception, FullInvnetoryException {
        String itemId = Game.player.inventory.getItemIdByName(itemName);
        if(itemId == null || Game.player.inventory.count(itemId) < 1) {
            throw new NoItemException(itemName);
        }
        String type = ItemDatabase.getItemInfo(itemId).data.get("equipment");
        String equipedId = "";
        switch(type) {
            case "Ring":
                if(this.gearList.get("Ring1").isEmpty()) {
                    this.gearList.put("Ring1", itemId);
                    Game.player.inventory.remove(new Item(itemId, 1));
                } else if (this.gearList.get("Ring2").isEmpty()){
                    this.gearList.put("Ring2", itemId);
                    Game.player.inventory.remove(new Item(itemId, 1));
                }else {
                    throw new NoAvailableSlotsException(type);
                }
                break;
            case "Weapon":
                WeaponInfo weaponInfo = WeaponDatabase.getWeaponInfo(itemId);
                equipedId = this.gearList.get(type);

                if((weaponInfo.type.equals("ranged") && Game.player.stats.get(weaponInfo.type).level >= weaponInfo.lvlReq) ||
                        (weaponInfo.type.equals("melee") && Game.player.stats.get("attack").level >= weaponInfo.lvlReq) ||
                        (weaponInfo.type.equals("magic") && Game.player.stats.get(weaponInfo.type).level >= weaponInfo.lvlReq)) {
                    if(!equipedId.isEmpty()) {
                        if(Game.player.inventory.getEmptySlots() > 0) {
                            Game.player.inventory.place(new Item(equipedId, 1));
                        } else {
                            throw new FullInvnetoryException();
                        }
                    }
                    this.gearList.put(type, itemId);
                    Game.player.inventory.remove(new Item(itemId, 1));
                } else {
                    throw new LvlReqNotMetexception(Game.player.stats.get(weaponInfo.type).name);
                }
                break;
            case "Ammunition":
                weaponInfo = WeaponDatabase.getWeaponInfo(itemId);
                if((weaponInfo.type.equals("ranged") && Game.player.stats.get(weaponInfo.type).level >= weaponInfo.lvlReq)) {
                    if(!this.ammunition.getKey().isEmpty()) {
                        int value = Integer.parseInt(this.ammunition.getValue());
                        int spaceNeeded = (int) Math.ceil((value + Game.player.inventory.count(itemId)) / ItemDatabase.getItemInfo(itemId).maxStack);
                        if(Game.player.inventory.getEmptySlots() >= spaceNeeded) {
                            Game.player.inventory.place(new Item(this.ammunition.getKey(), value));
                        } else {
                            throw new FullInvnetoryException();
                        }
                    }
                    this.ammunition.set(itemId, Integer.toString(Game.player.inventory.count(itemId)));
                    Game.player.inventory.remove(new Item(itemId, Game.player.inventory.count(itemId)));
                } else {
                    throw new LvlReqNotMetexception(Game.player.stats.get(weaponInfo.type).name);
                }
                break;
            default:
                ArmourInfo armourInfo = ArmourDatabase.getArmourInfo(itemId);
                equipedId = this.gearList.get(type);
                if(Game.player.stats.get("defence").level >= armourInfo.lvlReq) {
                    if(!equipedId.isEmpty()) {
                        if(Game.player.inventory.getEmptySlots() > 0) {
                            Game.player.inventory.place(new Item(equipedId, 1));
                        } else {
                            throw new FullInvnetoryException();
                        }
                    }
                    this.gearList.put(type, itemId);
                    Game.player.inventory.remove(new Item(itemId, 1));
                } else {
                    throw new LvlReqNotMetexception("Defence");
                }
                break;

        }
        Game.gui.updateGearGUI();
        Game.gui.updateInventoryGUI();
    }

    public void unEquip(String itemName) throws FullInvnetoryException {
        if(Game.player.inventory.getFilledSlots() == Game.player.inventory.size) {
            throw new FullInvnetoryException();
        }
        ItemInfo info;
        boolean found = false;
        for(var gear : this.gearList.entrySet()) {
            info = ItemDatabase.getItemInfo(gear.getValue());
            if(info != null && info.name.equals(itemName)) {
                Game.player.inventory.place(new Item(gear.getValue(), 1));
                this.gearList.put(gear.getKey(), "");
                found = true;
                break;
            }
        }
        if(!found) {
            Game.player.inventory.place(new Item(this.ammunition.getKey(), Integer.parseInt(this.ammunition.getValue())));
            this.ammunition.set("", "");
        }
        Game.gui.updateGearGUI();
        Game.gui.updateInventoryGUI();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(var gear : this.gearList.entrySet()) {
            sb.append(gear.getKey() + ": " + (gear.getValue().isEmpty() ? "" : ItemDatabase.getItemInfo(gear.getValue()).name) + "\n");
            if(gear.getKey().equals("Shield")) {
                sb.append("-----------------------------\n");
            }
        }
        sb.append("Ammunition: " + (this.ammunition.getValue().isEmpty() ? "" : this.ammunition.getValue() + " " + ItemDatabase.getItemInfo(this.ammunition.getKey()).name));

        return sb.toString();
    }
}
