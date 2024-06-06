package org.example;

import org.example.Controller.batchItemController;
import org.example.View.batchItemView;

public class MVCBatchItem {
    public static void main(String[] args) {

        Runtime.getRuntime().addShutdownHook(new Thread(() -> DatabaseConfiguration.Database.closeConnection()));

        batchItemView view = new batchItemView();
        Stock model = new Stock();

        BatchItemRepository batchItemRepository = new BatchItemRepository(DatabaseConfiguration.Database.getInstance());
        new batchItemController(view, model, batchItemRepository);


        view.setVisible(true);
    }
}
