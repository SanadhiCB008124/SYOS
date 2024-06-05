package org.example;

public class HolidayDiscount implements DiscountStrategy{

    private  double discount;

    public HolidayDiscount(double discount) {
        this.discount = discount;
    }

    @Override
    public double applyDiscount(double price) {
        return price*(1-discount);
    }
}
