package org.example.Controller;

import org.example.StockItemRepository;
import org.example.Stock;
import org.example.View.StockItemView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

public class StockItemController {

    private StockItemView theView;
    private Stock theModel;
    private StockItemRepository stockItemRepository;

    public StockItemController(StockItemView theView, Stock theModel, StockItemRepository stockItemRepository) {
        this.theView = theView;
        this.theModel = theModel;
        this.stockItemRepository = stockItemRepository;
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
                String itemName = theView.getItemName();
                Integer quantityInStock = Integer.parseInt(theView.getQuantityInStock().getText());
                expiryDate = parseDate(theView.getExpiryDate());
                manufactureDate = parseDate(theView.getManufactureDate());
                batchDate = parseDate(theView.getBatchDate());

                stockItemRepository.addBatchItems(batchCode, itemCode, itemName,quantityInStock,manufactureDate,expiryDate,batchDate);
                theView.displaySuccessMessage("Batch item added successfully.");
            } catch (NumberFormatException ex) {
                theView.displayError("Invalid input. Please enter valid numbers.");
            }
        }
        private java.sql.Date parseDate(String dateStr) {
            return java.sql.Date.valueOf(dateStr);
        }
    }
}
