package org.example;

public class CreditCardPayment implements PaymentStrategy{
    @Override
    public void pay(double amount) {
        System.out.println("Paying with card: " + amount);
    }

    @Override
    public void strategy() {
        String strategy = "Card";
    }
}
