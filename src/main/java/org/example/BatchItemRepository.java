package org.example;

import DatabaseConfiguration.Database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BatchItemRepository {

    private final Database database;
    public BatchItemRepository(Database database){
        this.database=database;
    }
    public void addBatchItems(Integer batchCode, Integer itemCode, Integer quantityInStock, Date manufactureDate, Date expiryDate, Date batchDate) {

        String SQL_INSERT = "INSERT INTO batchItem (batchCode, itemCode, quantityInStock, manufacturedate, expirydate, batchdate) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = database.connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL_INSERT)) {

            pstmt.setInt(1, batchCode);
            pstmt.setInt(2, itemCode);
            pstmt.setInt(3, quantityInStock);
            pstmt.setDate(4, manufactureDate);
            pstmt.setDate(5, expiryDate);
            pstmt.setDate(6, batchDate);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error adding Batch Item to database: " + e.getMessage());
        }
    }
}
