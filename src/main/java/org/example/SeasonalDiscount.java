package org.example;

public class SeasonalDiscount implements DiscountStrategy{
    private  double discount;

    public SeasonalDiscount(double discount){
        this.discount=discount;
    }

    @Override
    public double applyDiscount(double price) {

        return price * (1-discount);
    }
}
