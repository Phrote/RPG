package map;

public class Node {
    public int x;
    public int y;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int dist(Node o) {
        return Math.abs(this.x - o.x) + Math.abs(this.y-o.y);
    }

    public boolean isWithin(Node o, int dist) {
        return dist(o) <= dist;
    }
}
