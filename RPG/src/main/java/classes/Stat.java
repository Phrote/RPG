package classes;

public class Stat {
    public final String name;
    public int level;
    private double currentXp;
    private double xpToNextLevel;
    
    public Stat(String name) {
        this.name = name;
        this.level = 1;
        this.currentXp = 1.0;
        this.xpToNextLevel = 100.0;
    }
    
    public Stat(String name, int level, int currentXp, int xpToNextLvl) {
        this.name = name;
        this.level = level;
        this.currentXp = currentXp;
        this.xpToNextLevel = xpToNextLvl;
    }
    
    public double getXpToNextLevel() {
        return this.xpToNextLevel;
    }

    public double getCurrentXp() { return this.currentXp;}
    
    public void addXp(int xp) {
        this.currentXp += xp;
        while(this.currentXp > this.xpToNextLevel) {
            this.level++;
            this.currentXp -= this.xpToNextLevel;
            this.xpToNextLevel = this.xpToNextLevel * 1.12356;
        }
    }
    
    @Override
    public String toString() {
        return this.name + ": " + this.level;
    }
    
}
