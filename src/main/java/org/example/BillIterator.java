package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import DatabaseConfiguration.Database;

public class BillIterator implements Iterator{

    private List<Bill> bills;
    private BillRepository billRepository;
    private int position=0;

    public BillIterator(List<Bill> bills, BillRepository billRepository) {

        this.bills = bills;
        this.billRepository = billRepository;
    }

    @Override
    public boolean hasNext() {

        return position<bills.size();
    }

    @Override
    public Object next() {

        if (this.hasNext()) {
            return bills.get(position++);
        }
        return null;
    }

    public void loadBills() {
        List<Bill> loadedBills = billRepository.loadAllBills();
        bills.addAll(loadedBills);
    }
}
