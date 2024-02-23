package storage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StorageTest {
    public static final String item1 = "log";
    public static final String item2 = "rice";
    public static final ItemInfo item1_info = ItemDatabase.getItemInfo(item1);
    public static final ItemInfo item2_info = ItemDatabase.getItemInfo(item2);

    @Test
    void storageBaseTest() {
        int size = 5;
        StorageComponent storage = new StorageComponent(size);
        Item ret;

        ret = storage.place(new Item(item1,1));
        Assertions.assertNull(ret);
        Assertions.assertEquals(storage.count(item1), 1);

        ret = storage.place(new Item(item2,item2_info.maxStack));
        Assertions.assertNull(ret);
        Assertions.assertEquals(storage.count(item2), item2_info.maxStack);
        Assertions.assertEquals(storage.getFilledSlots(),2);
        Assertions.assertEquals(storage.getEmptySlots(),size-2);

        ret = storage.place(new Item(item2,item2_info.maxStack+1));
        Assertions.assertNull(ret);
        Assertions.assertEquals(storage.count(item2), item2_info.maxStack*2+1);
        Assertions.assertEquals(storage.getFilledSlots(),4);

        storage.remove(new Item(item2,item2_info.maxStack));
        Assertions.assertEquals(storage.count(item2), item2_info.maxStack+1);
        Assertions.assertEquals(storage.getFilledSlots(),3);
    }

    @Test
    void storageTextTest() {
        int size = 5;
        StorageComponent storage = new StorageComponent(size);
        storage.place(new Item(item1,item1_info.maxStack));
        storage.place(new Item(item2,item2_info.maxStack));

        Assertions.assertEquals(storage.textToItem("all " + item1_info.name), new Item(item1,item1_info.maxStack));
        Assertions.assertEquals(storage.textToItem("half of " + item1_info.name), new Item(item1,item1_info.maxStack/2));
        Assertions.assertEquals(storage.textToItem("third of " + item1_info.name), new Item(item1,item1_info.maxStack/3));
        Assertions.assertEquals(storage.textToItem("quarter " + item1_info.name), new Item(item1,item1_info.maxStack/4));
        Assertions.assertEquals(storage.textToItem("1 " + item1_info.name), new Item(item1,1));
        Assertions.assertEquals(storage.textToItem(item1_info.maxStack + " " + item1_info.name), new Item(item1,item1_info.maxStack));
        Assertions.assertEquals(storage.textToItem("an " + item1_info.name), new Item(item1,1));
    }

    @Test
    void storageTransferTest() {
        int inventorySize = 5;
        int bankSize = 3;
        StorageComponent inventory = new StorageComponent(inventorySize);
        StorageComponent bank = new StorageComponent(bankSize);

        inventory.place(new Item(item1, item1_info.maxStack*(inventorySize-1)));
        inventory.transfer(bank,inventory.textToItem("all " + item1_info.name));
        Assertions.assertEquals(bank.count(item1), item1_info.maxStack*bankSize);
        Assertions.assertEquals(inventory.count(item1), item1_info.maxStack*(inventorySize-1) - item1_info.maxStack*bankSize);

        bank.transfer(inventory, bank.textToItem("a " + item1_info.name));
        Assertions.assertEquals(bank.count(item1), item1_info.maxStack*bankSize-1);
        Assertions.assertEquals(inventory.count(item1), item1_info.maxStack*(inventorySize-1) - item1_info.maxStack*bankSize+1);

    }
}
