package org.example;

public class LoyaltyCustomer extends Customer{

    public LoyaltyCustomer(){
        this.customerType="Loyalty";
    }
    @Override
    public void setCustomerType() {
        this.customerType="Loyalty";
    }
}
