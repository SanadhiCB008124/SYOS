package org.example;

public class StockObserver extends Observer{

    public StockObserver(batchItem batchItem){
        this.batchItem=batchItem;
        this.batchItem.attach(this);
    }
    @Override
    public void update() {
        System.out.println("Observer is updated"+batchItem.getQuantityInStock());
    }

    @Override
    public void lowStockAlert() {
        System.out.println("Low Stock ! Re-order "+batchItem.getQuantityInStock() );
    }


}
