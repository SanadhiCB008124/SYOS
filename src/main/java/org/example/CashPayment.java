package org.example;

public class CashPayment implements PaymentStrategy{


    @Override
    public void pay(double amount) {
        System.out.println("Paying with cash: " + amount);
    }

    @Override
    public void strategy() {
        String strategy = "Cash";
    }
}
