package org.example;

import org.example.Reports.*;

import java.util.List;

public class ReportFacade {
    public void generateSalesReport() {
        Report report = new SalesReport();
        report.reportGenerator();
    }

    public void generateEndOfDayReport() {
        Report report = new EndOfDayReport();
        report.reportGenerator();
    }

    public void generateReOrderReport() {
        Report report = new ReOrderReport();
        report.reportGenerator();
    }

    public void generateStockReport() {
        Report report = new StockReport();
        report.reportGenerator();
    }

    public void generateBillReport(List<Bill> bills) {
        Report report = new BillReport(bills);
        report.reportGenerator();
    }
}
