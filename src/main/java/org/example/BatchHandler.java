package org.example;

import java.util.List;

public interface BatchHandler {
    void handleMovingItemsToTheShelf(List<batchItem> batchItems);
}
