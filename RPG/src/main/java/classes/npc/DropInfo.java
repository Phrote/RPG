package classes.npc;

public class DropInfo {
    public String id;
    public int minQty;
    public int maxQty;
    public double chance;

    public DropInfo(String id, int minQty, int maxQty, double chance){
        this.id = id;
        this.minQty = minQty;
        this.maxQty = maxQty;
        this.chance = chance;
    }

    @Override
    public String toString() {
        return "id: " + this.id + ", minQty: " + this.minQty + ", maxQty: " + this.maxQty + ", chance:" + this.chance;
    }
}
