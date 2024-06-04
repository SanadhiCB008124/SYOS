package org.example;

public class concreteObserver extends Observer{

    public concreteObserver(batchItem batchItem){
        this.batchItem=batchItem;
        this.batchItem.attach(this);
    }
    @Override
    public void update() {
        System.out.println("Observer is updated"+batchItem.getBatchCode());
    }
}
