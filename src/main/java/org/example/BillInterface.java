package org.example;

import java.util.HashMap;
import java.util.Map;

public class BillInterface implements PointOfSales{
    @Override
    public void getInterface() {
        BillBuilder builder = new ConcreteBillBuilder();
        ProductDetailsService productDetailsService = new ProductDetailsService();
        ItemDetailsService itemDetailsService=new ItemDetailsService(productDetailsService);
        BillSaveService billSaveService=new BillSaveService();
        Map<String,PaymentStrategy> strategyMap=new HashMap<>();
        strategyMap.put("Cash",new CashPayment());
        strategyMap.put("Credit Card",new CreditCardPayment());
        BillInterfaceService billInterfaceService=new BillInterfaceService(strategyMap);
        BillDirector billDirector=new BillDirector(builder,itemDetailsService,billSaveService,billInterfaceService);
        billDirector.checkState();
    }
}
