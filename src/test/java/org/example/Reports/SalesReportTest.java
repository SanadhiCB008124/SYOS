package org.example.Reports;

import org.example.Bill;
import org.example.BillItem;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SalesReportTest {

    private SalesReport salesReport;

    @Before
    public void setUp() {
        salesReport = new SalesReport();
    }

    @Test
    public void testGetData() {
        // Test getting data for sales report
        salesReport.getData();
        // Since the method is just printing, no need to verify interactions
    }

    @Test
    public void testCreateReport() {
        // Prepare test data
        List<BillItem> billItems = new ArrayList<>();
        billItems.add(new BillItem(1, 2, 10, 100.0));
        List<Bill> bills = new ArrayList<>();
        bills.add(new Bill(1, 200.0, billItems, 10.0, 210.0, 10.0, 190.0, new Date(), 2, "Cash", "John Doe"));
        salesReport.bills = bills;

        // Test creating the sales report
        salesReport.createReport();
        // Since the method is just printing, no need to verify interactions
    }
}
