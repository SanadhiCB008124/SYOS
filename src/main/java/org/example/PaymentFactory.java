package org.example;

public class PaymentFactory {

   public Payment getPaymentMethod(int choice){
       if(choice==1){
           return new CashPayment();
       }else {
           return null;
       }
   }

}
