package org.example;

import java.util.List;

public class ShelfHandler implements BatchHandler{
    @Override
    public void handleMovingItemsToTheShelf(List<batchItem> batchItems) {
        System.out.println("Items moving to the shelf:");
        for (batchItem item : batchItems) {
            System.out.println("Item Code: " + item.getItemCode() + ", Expiry Date: " + item.getExpiryDate());
            // Move item to shelf
        }
    }
}
