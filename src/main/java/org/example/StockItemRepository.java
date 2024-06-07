package org.example;

import DatabaseConfiguration.Database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StockItemRepository {

    private final Database database;
    public StockItemRepository(Database database){
        this.database=database;
    }
    public void addBatchItems(Integer batchCode, Integer itemCode,String itemName, Integer quantityInStock, Date manufactureDate, Date expiryDate, Date batchDate) {

        String SQL_INSERT = "INSERT INTO stockitem (batchCode, itemCode,itemname, quantityInStock, manufacturedate, expirydate, batchdate) VALUES (?,  ?, ?, ?, ?,?,?)";

        try (Connection conn = database.connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL_INSERT)) {

            pstmt.setInt(1, batchCode);
            pstmt.setInt(2, itemCode);
            pstmt.setString(3, itemName);
            pstmt.setInt(4, quantityInStock);
            pstmt.setDate(5, manufactureDate);
            pstmt.setDate(6, expiryDate);
            pstmt.setDate(7, batchDate);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error adding Batch Item to database: " + e.getMessage());
        }
    }
}
