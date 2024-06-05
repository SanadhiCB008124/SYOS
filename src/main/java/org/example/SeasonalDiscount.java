package org.example;

public class SeasonalDiscount implements DiscountStrategy{
    private final double discountAmount=100.00;
    Items items;


    @Override
    public double discount(double discount, Items items) {
        return items.getUnitPrice()-discount;
    }
}
