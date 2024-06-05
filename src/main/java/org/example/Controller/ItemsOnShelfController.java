package org.example.Controller;

import org.example.*;
import org.example.View.ItemsOnShelfView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ItemsOnShelfController {

    private ItemsOnShelfView theView;
    private Items theModel;

    public ItemsOnShelfController(ItemsOnShelfView theView, Items theModel) {
        this.theView = theView;
        this.theModel = theModel;

        initializeProducts();
        theView.addItemsOnShelfListener(new AddItemListener());
    }

    private void initializeProducts() {
        ProductIterator iterator = new ProductIterator();
        List<Product> products = new ArrayList<>();
        while (iterator.hasNext()) {
            products.add((Product) iterator.next());
        }
        theView.setProducts(products);

    }
    class AddItemListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {


            java.sql.Date expiryDate;
            java.sql.Date manufactureDate;

            try {
                int itemCode = Integer.parseInt(theView.getItemCode());
                String itemDescription = theView.getItemDescription();
                double unitPrice = Double.parseDouble(theView.getUnitPrice());
                int quantityOnShelf = Integer.parseInt(theView.getQuantityOnShelf());
                Product product = new Product(Integer.parseInt(theView.getProductID()), "");


                theModel.addItemsOnShelf(itemCode, itemDescription, unitPrice, quantityOnShelf,product);
                theView.displaySuccessMessage("Item added successfully!");
            } catch (Exception ex) {
                ex.printStackTrace();
                theView.displayErrorMessage("Error adding item: " + ex.getMessage());
            }
        }
    }



}
