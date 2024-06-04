package org.example;

public class CashPayment implements Payment{

    @Override
    public String paymentProcess() {

        return "cash";
    }
}
