package org.example;

public class ShelfObserver extends Observer {

    private Item item;

    public ShelfObserver(Item item){
        this.item = item;
        this.item.attach(this);
    }
    @Override
    public void update() {
        System.out.println("Shelf is updated"+ item.getQuantityOnShelf());
    }

    @Override
    public void lowStockAlert() {
        System.out.println("Low stock"+ item.getQuantityOnShelf());
    }
}
