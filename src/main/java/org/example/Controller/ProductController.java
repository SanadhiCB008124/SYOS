package org.example.Controller;

import org.example.Product;
import org.example.ProductCategory;
import org.example.ProductCategoryIterator;
import org.example.ProductView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ProductController {

    private ProductView theView;
    private Product theModel;

    public ProductController(ProductView theView, Product theModel) {
        this.theView = theView;
        this.theModel = theModel;

        initializeProductCategories();
        this.theView.addProductListener(new ProductListener());
    }

    private void initializeProductCategories() {
        ProductCategoryIterator iterator = new ProductCategoryIterator();
        List<ProductCategory> categories = new ArrayList<>();
        while (iterator.hasNext()) {
            categories.add((ProductCategory) iterator.next());
        }
        theView.setProductCategories(categories);
    }

    class ProductListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String productName;
            int productCategoryID;

            try {
                productName = theView.getProductName();
                ProductCategory selectedCategory = theView.getSelectedProductCategory();
                productCategoryID = selectedCategory.getProductCategoryId();

                theModel.addProduct(productName, productCategoryID);
                theView.setProduct(productName, selectedCategory.getProductCategoryName());
                theView.displaySuccessMessage("Product added successfully!");

            } catch (Exception exception) {
                theView.displayError("Error: " + exception.getMessage());
            }
        }
    }
}

