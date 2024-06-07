package org.example;

public class StockToShelfCheckInterface  implements PointOfSales{
    @Override
    public void getInterface() {
            StockItemRepository stockItemRepository = new StockItemRepository(DatabaseConfiguration.Database.getInstance());
            StockToShelf stockToShelf = new StockToShelf(stockItemRepository);
            stockToShelf.moveItemsToShelfFromDatabase();

    }
}
