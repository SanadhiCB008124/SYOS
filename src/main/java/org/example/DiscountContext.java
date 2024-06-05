package org.example;

public class DiscountContext {
    private DiscountStrategy discountStrategy;

    public DiscountContext(DiscountStrategy discountStrategy){
        this.discountStrategy=discountStrategy;

    }

    public double applyDiscount(double price){
        return discountStrategy.applyDiscount(price);
    }
}
