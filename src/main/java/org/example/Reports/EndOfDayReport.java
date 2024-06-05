package org.example.Reports;

import org.example.Context;
import org.example.POSState;
import org.example.Report;

public class EndOfDayReport extends Report implements POSState {
    @Override
    public void getData() {

        System.out.println("Getting data for End of Day Report");
    }

    @Override
    public void createReport() {

        System.out.println("Creating End of Day Report");
    }


    @Override
    public void checkState(Context context) {
        System.out.println("Printing End of Day Report");
        context.setState(this);
    }
}
