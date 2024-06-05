package org.example;

public class HolidayDiscount implements DiscountStrategy{

    private final double discount=200.00;

    @Override
    public double discount(double discount, Items items) {
        return items.getUnitPrice()-discount;
    }
}
