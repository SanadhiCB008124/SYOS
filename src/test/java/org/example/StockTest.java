package org.example;

import org.junit.Test;
import static org.junit.Assert.*;

public class StockTest {

    private Item item;

    @Test
    public void testStockGettersAndSetters() {
        // Create a Stock instance
        Stock stock = new Stock();
        Item item = new Item();
        // Set values using setters
        stock.setItemName("Test Item");
        stock.setItemCode(123);
        stock.setBatchCode(456);
        stock.setQuantityInStock(100);

        // Set dates
        java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
        stock.setExpiryDate(currentDate);
        stock.setManufactureDate(currentDate);
        stock.setBatchDate(currentDate);

        // Test getters
        assertEquals("Test Item", stock.getItemName());
        assertEquals(Integer.valueOf(123), stock.getItemCode());
        assertEquals(Integer.valueOf(456), stock.getBatchCode());
        assertEquals(Integer.valueOf(100), stock.getQuantityInStock());
        assertEquals(currentDate, stock.getExpiryDate());
        assertEquals(currentDate, stock.getManufactureDate());
        assertEquals(currentDate, stock.getBatchDate());
    }

    @Test
    public void testCheckLowStock() {
        // Create a Stock instance with low quantity
        Stock stock = new Stock();
        stock.setQuantityInStock(40);

        // Create a mock observer
        MockObserver observer = new MockObserver();
        stock.attach(observer);

        // Trigger low stock check
        stock.checkLowStock();

        // Verify that the observer was notified
        assertTrue(observer.wasUpdateCalled());
        assertTrue(observer.wasLowStockAlertCalled());
    }

    // Mock Observer class for testing
    class MockObserver extends Observer {
        private boolean updateCalled = false;
        private boolean lowStockAlertCalled = false;

        @Override
        public void update() {
            updateCalled = true;
        }

        @Override
        public void lowStockAlert() {
            lowStockAlertCalled = true;
        }

        public boolean wasUpdateCalled() {
            return updateCalled;
        }

        public boolean wasLowStockAlertCalled() {
            return lowStockAlertCalled;
        }
    }
}
