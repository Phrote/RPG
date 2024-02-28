package map;

public class PlaceInfoBase {
    public String name;
    public String description;

    public Node node;

    public PlaceInfoBase(String name, String description, Node node) {
        this.name = name;
        this.description = description;
        this.node = node;
    }
}
