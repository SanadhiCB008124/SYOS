package org.example.Controller;

import org.example.Stock;

import org.example.View.stockView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class stockController {

    private stockView theView;
    private Stock theModel;


    public stockController(stockView theView, Stock theModel) {
        this.theView = theView;
        this.theModel = theModel;

        this.theView.addStockListener(new StockListener());


    }

    class StockListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            Integer batchCode;
            java.sql.Date batchDate;

            try{
                   batchCode = theView.getBatchCode();
                   batchDate = theView.getBatchDate();

                    theModel.addStockRecord(batchCode, batchDate);
                    theView.setStock(batchCode, batchDate);
                    theView.displaySuccessMessage("stock record added successfully!");



            } catch (Exception exception) {
                theView.displayError("Error: " + exception.getMessage());
            }

        }
    }


}
