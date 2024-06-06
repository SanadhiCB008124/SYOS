package org.example;

import java.util.ArrayList;
import java.util.List;

public class ExpiryDateHandler implements BatchHandler{

    private BatchHandler nextHandler;

    public void setNextHandler(BatchHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public void handleMovingItemsToTheShelf(List<batchItem> batchItems) {

        List<batchItem> sortedItems=new ArrayList<>();


        for(batchItem item:batchItems){
            if(item.getBatchDate().before(item.getExpiryDate())){
                sortedItems.add(item);
            }
        }

        if (nextHandler != null) {
            nextHandler.handleMovingItemsToTheShelf(sortedItems);
        }


    }
}
