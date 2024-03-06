package classes;

import Exceptions.FullInvnetoryException;
import Exceptions.LvlReqNotMetexception;
import Exceptions.NoAvailableSlotsException;
import Exceptions.NoItemException;
import item.*;
import main.Game;
import utils.Pair;
import utils.Utils;


import java.util.LinkedHashMap;

public class Gear {
    private LinkedHashMap<String, String> gearList = new LinkedHashMap<>();
    private Pair<String, Integer> ammunition;

    public Gear() {
        gearList.put("Weapon", "");
        gearList.put("Shield", "");
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

    public Gear(Gear gear) {
        gearList.putAll(gear.gearList);
        this.ammunition = new Pair<>(null, 0);
    }

    public void equip(String itemName) throws NoItemException, NoAvailableSlotsException, LvlReqNotMetexception, FullInvnetoryException {
        if(Game.player.inventory.count(itemName) < 1) {
            throw new NoItemException(itemName);
        }
        String type = ItemDatabase.getItemInfo(itemName).data.get("equipment");
        String equipedId = "";
        switch(type) {
            case "Ring":
                if(this.gearList.get("Ring1").isEmpty()) {
                    this.gearList.put("Ring1", itemName);
                    Game.player.inventory.remove(new Item(itemName, 1));
                } else if (this.gearList.get("Ring2").isEmpty()){
                    this.gearList.put("Ring2", itemName);
                    Game.player.inventory.remove(new Item(itemName, 1));
                }else {
                    throw new NoAvailableSlotsException(type);
                }
                break;
            case "Weapon":
                WeaponInfo weaponInfo = WeaponDatabase.getWeaponInfo(itemName);
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
                    this.gearList.put(type, itemName);
                    Game.player.inventory.remove(new Item(itemName, 1));
                } else {
                    throw new LvlReqNotMetexception(weaponInfo.type);
                }
                break;
            case "Ammunition":
                weaponInfo = WeaponDatabase.getWeaponInfo(itemName);
                if((weaponInfo.type.equals("Ranged") && Game.player.stats.get(weaponInfo.type).level >= weaponInfo.lvlReq)) {
                    if(!this.ammunition.key.isEmpty()) {
                        int value = this.ammunition.value;
                        int spaceNeeded = (int) Math.ceil((value + Game.player.inventory.count(itemName)) / ItemDatabase.getItemInfo(itemName).maxStack);
                        if(Game.player.inventory.getEmptySlots() >= spaceNeeded) {
                            Game.player.inventory.place(new Item(this.ammunition.key, value));
                        } else {
                            throw new FullInvnetoryException();
                        }
                    }
                    this.ammunition.set(itemName, Game.player.inventory.count(itemName));
                    Game.player.inventory.remove(new Item(itemName, Game.player.inventory.count(itemName)));
                } else {
                    throw new LvlReqNotMetexception(weaponInfo.type);
                }
                break;
            default:
                ArmourInfo armourInfo = ArmourDatabase.getArmourInfo(itemName);
                equipedId = this.gearList.get(type);
                if(Game.player.stats.get("Defence").level >= armourInfo.lvlReq) {
                    if(!equipedId.isEmpty()) {
                        if(Game.player.inventory.getEmptySlots() > 0) {
                            Game.player.inventory.place(new Item(equipedId, 1));
                        } else {
                            throw new FullInvnetoryException();
                        }
                    }
                    this.gearList.put(type, itemName);
                    Game.player.inventory.remove(new Item(itemName, 1));
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
        boolean found = false;
        for(var gear : this.gearList.entrySet()) {
            if(itemName.equals(gear.getValue())) {
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

    public int calcFlatBlock() {
        int block = 0;
        for(var gear : this.gearList.entrySet()) {
            if(gear.getValue().isEmpty()) { continue; }
            if(gear.getKey().equals("Weapon")) { continue; }
            block += ArmourDatabase.getArmourInfo(gear.getValue()).block;
        }
        return block;
    }

    public int calcFlatDmg() {
        int dmg = 0;
        if(!this.gearList.get("Weapon").isEmpty()) {
            WeaponInfo info = WeaponDatabase.getWeaponInfo(this.gearList.get("Weapon"));
            dmg = info.dmg;
            if(info.type.equals("Ranged") && this.ammunition.value != 0) {
                dmg += WeaponDatabase.getWeaponInfo(this.ammunition.key).dmg;
            } else if(info.type.equals("Magic")) {
                //TODO
            }
        }
        return dmg;
    }

    public double getAttackSpeed() {
        if(!this.gearList.get("Weapon").isEmpty()) {
            return WeaponDatabase.getWeaponInfo(this.gearList.get("Weapon")).attackSpeed;
        }
        return 1.5;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(var gear : this.gearList.entrySet()) {
            sb.append(gear.getKey() + ": " + gear.getValue() + "\n");
            if(gear.getKey().equals("Shield")) {
                sb.append("-----------------------------\n");
            }
        }
        sb.append("Ammunition: " + (this.ammunition.key == null ? "" : this.ammunition.value + " " + this.ammunition.key));

        return sb.toString();
    }

    public String completeGearName(String text) {
        String bestPrefix = null;
        for(var gear : gearList.entrySet()) {
            if(gear.getValue() == null || gear.getValue().isEmpty())
                continue;
            if(gear.getValue().startsWith(text)) {
                if (bestPrefix == null) {
                    bestPrefix = gear.getValue();
                } else {
                    bestPrefix = Utils.commonPrefix(bestPrefix, gear.getValue());
                }
            }
        }
        if(ammunition.key != null) {
            if(ammunition.key.startsWith(text)) {
                if (bestPrefix == null) {
                    bestPrefix = ammunition.key;
                } else {
                    bestPrefix = Utils.commonPrefix(bestPrefix, ammunition.key);
                }
            }
        }
        return bestPrefix;
    }

    public void wearGearSet(boolean forceSwitchAll) {
        StorageComponent inventory = Game.player.inventory;
        Gear gear = Game.player.gear;
        String worn;

        for (var gearPair : gearList.entrySet()) {

            if(!gearPair.getValue().isEmpty() || forceSwitchAll) {
                worn = gear.gearList.get(gearPair.getKey());
                if (worn != null && !worn.isEmpty()) {
                    try {
                        gear.unEquip(worn);
                    } catch (FullInvnetoryException e) {
                        continue;
                    }
                }
            }
            if(!gearPair.getValue().isEmpty()) {
                try {
                    gear.equip(gearPair.getValue());
                } catch (NoItemException | NoAvailableSlotsException | LvlReqNotMetexception |
                         FullInvnetoryException e) {
                    continue;
                }
            }
        }
    }
}
