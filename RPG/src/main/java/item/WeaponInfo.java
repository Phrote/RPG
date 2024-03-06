package item;

public class WeaponInfo {
    public String type;
    public int lvlReq;
    public double attackSpeed;
    public int dmg;

    public WeaponInfo(String type, int lvlReq, double attackSpeed, int dmg) {
        this.type = type;
        this.lvlReq = lvlReq;
        this.attackSpeed = attackSpeed;
        this.dmg = dmg;
    }
}
