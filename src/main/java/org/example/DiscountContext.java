package org.example;

public class DiscountContext {
    private DiscountStrategy discountStrategy;

    public DiscountContext(DiscountStrategy discountStrategy){
        this.discountStrategy=discountStrategy;

    }

    public double executeStrategy(double discount, Items items){
        return discountStrategy.discount(discount,items);
    }
}
