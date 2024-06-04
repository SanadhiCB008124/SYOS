package org.example.Reports;

import org.example.Report;

public class ReOrderReport extends Report {
    @Override
    protected void getData() {

        System.out.println("Getting data for ReOrder Report");
    }

    @Override
    protected void createReport() {
        System.out.println("Creating ReOrder Report");

    }


}
