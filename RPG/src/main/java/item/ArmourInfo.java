package item;

import java.util.HashMap;
import java.util.Map;

public class ArmourInfo {
    public int lvlReq;
    public int block;
    public Map<String, String> data;


    public ArmourInfo(int lvlReq, int block) {
        this.lvlReq = lvlReq;
        this.block = block;
        this.data = new HashMap<>();
    }
}
