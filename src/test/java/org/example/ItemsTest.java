package org.example;

import junit.framework.TestCase;
import java.util.Date;

public class ItemsTest extends TestCase {

    private Items item;

    public void setUp() throws Exception {
        super.setUp();
        item = new Items(3, "Test Item", 10.0);
    }

    public void tearDown() throws Exception {
        item = null;
    }

    public void testGetItemCode() {
        assertEquals((Integer) 3, item.getItemCode());
    }

    public void testSetItemCode() {
        item.setItemCode(2);
        assertEquals((Integer) 2, item.getItemCode());
    }

    public void testGetItemDescription() {
        assertEquals("Test Item", item.getItemDescription());
    }

    public void testSetItemDescription() {
        item.setItemDescription("Updated Item");
        assertEquals("Updated Item", item.getItemDescription());
    }

    public void testGetUnitPrice() {
        assertEquals(10.0, item.getUnitPrice());
    }

    public void testSetUnitPrice() {
        item.setUnitPrice(20.0);
        assertEquals(20.0, item.getUnitPrice());
    }



    public void testGetQuantityOnShelf() {
        item.setQuantityOnShelf(100);
        assertEquals((Integer) 100, item.getQuantityOnShelf());
    }

    public void testSetQuantityOnShelf() {
        item.setQuantityOnShelf(200);
        assertEquals((Integer) 200, item.getQuantityOnShelf());
    }

    public void testAddItemsOnShelf() {
        Product product = new Product();
        item.addItemsOnShelf(1, "NewItem", 10.0, 50, product, new Date(), new Date());
        assertEquals((Integer) 1, item.getItemCode());
        assertEquals("NewItem", item.getItemDescription());
        assertEquals(10.0, item.getUnitPrice());
        assertEquals((Integer) 50, item.getQuantityOnShelf());
    }
}
