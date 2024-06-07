package org.example;

import java.util.ArrayList;
import java.util.List;

public class ExpiryDateHandler implements BatchHandler{

    private BatchHandler nextHandler;

    public void setNextHandler(BatchHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public void handleMovingItemsToTheShelf(List<Stock> Stocks) {

        List<Stock> sortedItems=new ArrayList<>();

        for(Stock item: Stocks){
            if(item.getBatchDate().before(item.getExpiryDate())){
                sortedItems.add(item);
            }
        }

        if (nextHandler != null) {
            nextHandler.handleMovingItemsToTheShelf(sortedItems);
        }


    }
}
