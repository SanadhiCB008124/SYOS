package org.example.Reports;

import org.example.Report;

public class SalesReport extends Report {

    @Override
    protected void getData() {
        System.out.println("Getting data for Sales Report");

    }

    @Override
    protected void createReport() {
        System.out.println("Creating Sales Report");

    }


}
