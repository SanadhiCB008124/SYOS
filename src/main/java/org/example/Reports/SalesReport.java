package org.example.Reports;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import DatabaseConfiguration.Database;
import org.example.*;

public class SalesReport extends Report {
    private List<Bill> bills = new ArrayList<>();


    @Override
    protected void getData() {
        System.out.println("Getting data for Sales Report");
        BillRepository billRepository=new BillRepository();
        BillIterator billIterator=new BillIterator(bills, billRepository);
        billIterator.loadBills();


    }

    @Override
    protected void createReport() {
        System.out.println("Creating Sales Report");
        double totalRevenue=0.00;


        SalesPerDayCriteria salesPerDayCriteria = new SalesPerDayCriteria();
        List<Bill> filteredBills = salesPerDayCriteria.FilterBillsByDay(bills);
        salesPerDayCriteria.FilterBillsByDay(bills);

        for (Bill bill : filteredBills) {
            System.out.println(bill);
            totalRevenue+=bill.getNetTotal();
        }

        System.out.println("Total Revenue  :"+ totalRevenue);
    }
}
