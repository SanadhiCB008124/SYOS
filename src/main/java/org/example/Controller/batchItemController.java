package org.example.Controller;

import org.example.BatchItemRepository;
import org.example.Stock;
import org.example.View.batchItemView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class batchItemController {

    private batchItemView theView;
    private Stock theModel;
    private BatchItemRepository batchItemRepository;

    public batchItemController(batchItemView theView, Stock theModel, BatchItemRepository batchItemRepository) {
        this.theView = theView;
        this.theModel = theModel;
        this.batchItemRepository=batchItemRepository;
        this.theView.addBatchItemListener(new AddBatchItemListener());
    }

    class AddBatchItemListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            java.sql.Date expiryDate;
            java.sql.Date manufactureDate;
            java.sql.Date batchDate;

            try {
                Integer itemCode = Integer.parseInt(theView.getItemCode());
                Integer batchCode = theView.getBatchCode();
                Integer quantityInStock = Integer.parseInt(theView.getQuantityInStock().getText());
                expiryDate = theView.getExpiryDate();
                manufactureDate=theView.getManufactureDate();
                batchDate=theView.getBatchDate();

                batchItemRepository.addBatchItems(batchCode, itemCode, quantityInStock,manufactureDate,expiryDate,batchDate);
                theView.displaySuccessMessage("Batch item added successfully.");
            } catch (NumberFormatException ex) {
                theView.displayError("Invalid input. Please enter valid numbers.");
            }
        }
    }
}
