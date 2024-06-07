package org.example;

import DatabaseConfiguration.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StockToShelf {
    private BatchHandler batchHandler;

    public StockToShelf() {
        ExpiryDateHandler expiryDateHandler = new ExpiryDateHandler();
        ShelfHandler shelfHandler = new ShelfHandler();

        expiryDateHandler.setNextHandler(shelfHandler);
        batchHandler = expiryDateHandler;
    }

    public void moveItemsToShelfFromDatabase() {
        try (Connection conn = Database.connect()) {

            List<Stock> Stocks = retrieveBatchItemsFromDatabase(conn);
            batchHandler.handleMovingItemsToTheShelf(Stocks);
        } catch (SQLException e) {
            System.out.println("Error moving items to shelf: " + e.getMessage());
        }
    }

    private List<Stock> retrieveBatchItemsFromDatabase(Connection conn) throws SQLException {
        List<Stock> Stocks = new ArrayList<>();
        String SQL_SELECT = "SELECT * FROM stockitem";

        try (PreparedStatement pstmt = conn.prepareStatement(SQL_SELECT)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int stockItemId = rs.getInt("stockItemId");
                int batchCode = rs.getInt("batchCode");
                String itemName = rs.getString("itemName");
                int itemCode = rs.getInt("itemCode");
                int quantityInStock = rs.getInt("quantityInStock");
                Date manufactureDate = rs.getDate("manufactureDate");
                Date expiryDate = rs.getDate("expiryDate");
                Date batchDate = rs.getDate("batchDate");

                Stocks.add(new Stock(stockItemId, batchCode, itemName, itemCode, quantityInStock, expiryDate, manufactureDate,batchDate));
            }
        }

        return Stocks;
    }

    public static void main(String[] args) {
        StockToShelf stockToShelf = new StockToShelf();
        stockToShelf.moveItemsToShelfFromDatabase();
    }
}
