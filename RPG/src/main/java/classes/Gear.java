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
    private Pair<String, Integer> ammunition;

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
        this.ammunition = new Pair(null, 0);
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

                if((weaponInfo.type.equals("Ranged") && Game.player.stats.get(weaponInfo.type).level >= weaponInfo.lvlReq) ||
                        (weaponInfo.type.equals("Attack") && Game.player.stats.get("Attack").level >= weaponInfo.lvlReq) ||
                        (weaponInfo.type.equals("Magic") && Game.player.stats.get(weaponInfo.type).level >= weaponInfo.lvlReq)) {
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
                    throw new LvlReqNotMetexception(weaponInfo.type);
                }
                break;
            case "Ammunition":
                weaponInfo = WeaponDatabase.getWeaponInfo(itemId);
                if((weaponInfo.type.equals("Ranged") && Game.player.stats.get(weaponInfo.type).level >= weaponInfo.lvlReq)) {
                    if(!this.ammunition.key.isEmpty()) {
                        int value = this.ammunition.value;
                        int spaceNeeded = (int) Math.ceil((value + Game.player.inventory.count(itemId)) / ItemDatabase.getItemInfo(itemId).maxStack);
                        if(Game.player.inventory.getEmptySlots() >= spaceNeeded) {
                            Game.player.inventory.place(new Item(this.ammunition.key, value));
                        } else {
                            throw new FullInvnetoryException();
                        }
                    }
                    this.ammunition.set(itemId, Game.player.inventory.count(itemId));
                    Game.player.inventory.remove(new Item(itemId, Game.player.inventory.count(itemId)));
                } else {
                    throw new LvlReqNotMetexception(weaponInfo.type);
                }
                break;
            default:
                ArmourInfo armourInfo = ArmourDatabase.getArmourInfo(itemId);
                equipedId = this.gearList.get(type);
                if(Game.player.stats.get("Defence").level >= armourInfo.lvlReq) {
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
            Game.player.inventory.place(new Item(this.ammunition.key, this.ammunition.value));
            this.ammunition.set(null, 0);
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
        sb.append("Ammunition: " + (this.ammunition.key == null ? "" : this.ammunition.value + " " + ItemDatabase.getItemInfo(this.ammunition.key).name));

        return sb.toString();
    }
}
