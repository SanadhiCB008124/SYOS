package org.example;

import org.example.Controller.stockController;
import org.example.View.stockView;

public class MVCStock {

    public static void main(String[] args) {

        Runtime.getRuntime().addShutdownHook(new Thread(() -> DatabaseConfiguration.Database.closeConnection()));

        stockView view = new stockView();
        Stock model = new Stock();
        stockController controller = new stockController(view, model);

        view.setVisible(true);
    }
}
