package org.example;

import java.sql.*;
import java.util.List;

import DatabaseConfiguration.Database;

public class StockIterator implements Iterator {

    private List<Stock> stocks;
    private int position = 0;

    public StockIterator(List<Stock> stocks) {
        this.stocks = stocks;
        
    }

    public void loadStock(){

        String SQL_SELECT = "SELECT batchitemid, itemcode, batchcode,quantityinstock,expirydate,manufacturedate,batchdate FROM batchitem";

        try (Connection conn = Database.connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL_SELECT);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                int batchItemId = rs.getInt("batchitemid");
                int itemCode = rs.getInt("itemcode");
                int batchCode = rs.getInt("batchcode");
                int quantityInStock = rs.getInt("quantityinstock");
                Date expiryDate = rs.getDate("expirydate");
                Date manufactureDate = rs.getDate("manufacturedate");
                Date batchDate = rs.getDate("batchdate");
                stocks.add(new Stock(batchItemId, itemCode, batchCode, quantityInStock, expiryDate, manufactureDate, batchDate));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error loading stocks: " + e.getMessage());
        }

    }

    public void reOrderStockLevels(){

        String SQL_SELECT = "SELECT batchitemid, itemcode, batchcode, quantityinstock, expirydate, manufacturedate, batchdate " + "FROM batchitem " + "WHERE quantityinstock < 50";

        try (Connection conn = Database.connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL_SELECT);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                int batchItemId = rs.getInt("batchitemid");
                int itemCode = rs.getInt("itemcode");
                int batchCode = rs.getInt("batchcode");
                int quantityInStock = rs.getInt("quantityinstock");
                Date expiryDate = rs.getDate("expirydate");
                Date manufactureDate = rs.getDate("manufacturedate");
                Date batchDate = rs.getDate("batchdate");
                stocks.add(new Stock(batchItemId, itemCode, batchCode, quantityInStock, expiryDate, manufactureDate, batchDate));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error loading stocks: " + e.getMessage());
        }

    }

    @Override
    public boolean hasNext() {
       return position<stocks.size();
    }

    @Override
    public Object next() {
        if (this.hasNext()) {
            return stocks.get(position++);
        }
        return null;
    }


}
