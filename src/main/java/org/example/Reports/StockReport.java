package org.example.Reports;

import org.example.Report;
import org.example.Stock;
import org.example.StockIterator;

import java.util.ArrayList;
import java.util.List;

public class StockReport extends Report {

    private List<Stock> stocks=new ArrayList<>();
    @Override
    protected void getData() {

        //getting the stock data through the iterator
        System.out.println("Getting data for Stock Report");
        StockIterator stockIterator=new StockIterator(stocks);
        stockIterator.loadStock();

    }

    @Override
    protected void createReport() {
        System.out.println("Creating Stock Report");
        for (Stock stock : stocks) {
            System.out.println("Batch Code: " + stock.getBatchCode());
            System.out.println("Item Code: " + stock.getItemCode());
            System.out.println("Quantity in Stock: " + stock.getQuantityInStock());
            System.out.println("Expiry Date: " + stock.getExpiryDate());
            System.out.println("Manufacture Date: " + stock.getManufactureDate());
            System.out.println("Batch Date: " + stock.getBatchDate());
            System.out.println("---------------------------------------------");
        }
    }


}
