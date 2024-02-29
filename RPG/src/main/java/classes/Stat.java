package classes;

import utils.Utils;

public class Stat {
    public int level;
    public double currentXp;
    
    public Stat() {
        this.level = 1;
        this.currentXp = 1.0;
    }
    
    public Stat(int level, int currentXp) {
        this.level = level;
        this.currentXp = currentXp;
    }

    public void addXp(int xp) {
        this.currentXp += xp;
        while(this.currentXp > Utils.xpLevels[this.level+1]) {
            this.level++;
        }
    }
    
    @Override
    public String toString() {
        return Integer.toString(this.level);
    }
    
}
