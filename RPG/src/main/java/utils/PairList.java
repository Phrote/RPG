package utils;

import java.util.ArrayList;

public class PairList<K,V> {
    private ArrayList<Pair<K,V>> list;

    public PairList() {
        list = new ArrayList<>();
    }

    public void add(K key, V value) {
        list.add(new Pair<K,V>(key, value));
    }

    public V getValue(String key) {
        for(Pair<K,V> p : list) {
            if(p.key.equals(key)) {
                return p.value;
            }
        }
        return null;
    }

    public K getKey(String value) {
        for(Pair<K,V> p : list) {
            if(p.value.equals(value)) {
                return p.key;
            }
        }
        return null;
    }

}
