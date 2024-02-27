package utils;

import java.util.ArrayList;

public class PairList {
    private ArrayList<Pair> list;

    public PairList() {
        list = new ArrayList<>();
    }

    public void add(String key, String value) {
        list.add(new Pair(key, value));
    }

    public String getValue(String key) {
        for(Pair p : list) {
            if(p.getKey().equals(key)) {
                return p.getValue();
            }
        }
        return null;
    }

    public String getKey(String value) {
        for(Pair p : list) {
            if(p.getValue().equals(value)) {
                return p.getKey();
            }
        }
        return null;
    }

}
