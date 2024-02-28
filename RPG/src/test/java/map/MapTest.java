package map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MapTest {
    @Test
    void testNode() {
        Node from = new Node(1,2);
        Node to = new Node(3,4);
        Assertions.assertEquals(from.dist(to),4);
        Assertions.assertTrue(to.isWithin(from,5));
        Assertions.assertFalse(to.isWithin(from,3));
    }

    @Test
    void testMap() {
        Assertions.assertTrue(Map.getPlacesInRange("first_village",5).size() > 0);
    }
}
