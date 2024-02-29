package utils;

public class Pair {
    private String key;
    private String value;

    public Pair() {
        this.key = "";
        this.value = "";
    }
    public Pair(String key, String value) {
        this.key = key;
        this. value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void set(String key, String value) {
        this.key = key;
        this.value = value;
    }
}
