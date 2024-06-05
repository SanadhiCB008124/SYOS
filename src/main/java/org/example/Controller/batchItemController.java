package org.example.Controller;

import org.example.batchItem;
import org.example.View.batchItemView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class batchItemController {

    private batchItemView theView;
    private batchItem theModel;

    public batchItemController(batchItemView theView, batchItem theModel) {
        this.theView = theView;
        this.theModel = theModel;


        this.theView.addBatchItemListener(new AddBatchItemListener());
    }

    class AddBatchItemListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            java.sql.Date expiryDate;
            java.sql.Date manufactureDate;

            try {
                Integer itemCode = Integer.parseInt(theView.getItemCode());
                Integer batchCode = theView.getBatchCode();
                Integer quantityInStock = Integer.parseInt(theView.getQuantityInStock().getText());
                expiryDate = theView.getExpiryDate();
                manufactureDate=theView.getManufactureDate();

                theModel.addBatchItems(batchCode, itemCode, quantityInStock,manufactureDate,expiryDate);
                theView.displaySuccessMessage("Batch item added successfully.");
            } catch (NumberFormatException ex) {
                theView.displayError("Invalid input. Please enter valid numbers.");
            }
        }
    }
}
