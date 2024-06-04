package org.example.Reports;

import org.example.Report;

public class StockReport extends Report {
    @Override
    protected void getData() {
        System.out.println("Getting data for Stock Report");

    }

    @Override
    protected void createReport() {
        System.out.println("Creating Stock Report");

    }


}
