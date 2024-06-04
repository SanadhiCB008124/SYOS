package org.example;

public class ShelfObserver extends Observer {

    public ShelfObserver(Items items){
        this.items=items;
        this.items.attach(this);
    }
    @Override
    public void update() {
        System.out.println("Shelf is updated"+items.getQuantityOnShelf());
    }
}
