package org.example;

import org.example.Controller.batchItemController;
import org.example.View.batchItemView;

public class MVCBatchItem {
    public static void main(String[] args) {

        Runtime.getRuntime().addShutdownHook(new Thread(() -> DatabaseConfiguration.Database.closeConnection()));

        batchItemView view = new batchItemView();
        batchItem model = new batchItem();
        batchItemController controller = new batchItemController(view, model);

        view.setVisible(true);
    }
}
