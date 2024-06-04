package org.example;


import org.example.Controller.ProductController;

public class MVCProduct {
    public static void main(String[] args) {

        Runtime.getRuntime().addShutdownHook(new Thread(() -> DatabaseConfiguration.Database.closeConnection()));

        ProductView view = new ProductView();
        Product model = new Product();
        ProductController controller = new ProductController(view, model);

        view.setVisible(true);
    }
}
