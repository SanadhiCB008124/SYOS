package org.example;

public class RegularCustomer extends Customer{
    public RegularCustomer(){
        this.customerType="Regular";
    }

    @Override
    public void setCustomerType() {
        this.customerType="Regular";
    }
}
