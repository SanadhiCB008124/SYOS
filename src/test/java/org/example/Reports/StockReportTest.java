package org.example.Reports;

import org.example.Stock;
import org.example.StockIterator;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class StockReportTest {

    private StockReport stockReport;

    @Before
    public void setUp() {
        stockReport = new StockReport();
    }

    @Test
    public void testGetData() {
        // Test getting data for stock report
        stockReport.getData();
        // Since the method is just printing, no need to verify interactions
    }

    @Test
    public void testCreateReport() {
        // Prepare test data
        List<Stock> stocks = new ArrayList<>();
        stocks.add(new Stock(1, "Test Item", 123, 456, 100, null, null, null));
        stockReport.stocks = stocks;

        // Test creating the stock report
        stockReport.createReport();
        // Since the method is just printing, no need to verify interactions
    }
}
