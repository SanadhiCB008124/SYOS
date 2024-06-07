package org.example;

import org.example.Controller.StockItemController;
import org.example.View.StockItemView;

public class StockInterface implements PointOfSales {

    @Override
    public void getInterface() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> DatabaseConfiguration.Database.closeConnection()));

        StockItemView view = new StockItemView();
        Stock model = new Stock();

        StockItemRepository stockItemRepository = new StockItemRepository(DatabaseConfiguration.Database.getInstance());
        new StockItemController(view, model, stockItemRepository);


        view.setVisible(true);
    }
}
