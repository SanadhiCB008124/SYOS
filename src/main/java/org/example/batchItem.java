package org.example;


import DatabaseConfiguration.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class batchItem {

    private Integer batchItemId;
    private Integer batchCode;
    private Integer itemCode;
    private Integer quantityInStock;

    public batchItem(Integer batchItemId, Integer batchCode, Integer itemCode, Integer quantityInStock) {
        this.batchItemId = batchItemId;
        this.batchCode = batchCode;
        this.itemCode = itemCode;
        this.quantityInStock = quantityInStock;
    }

    public batchItem() {

    }

    public void addBatchItems(Integer batchCode, Integer itemCode, Integer quantityInStock) {

        String SQL_INSERT = "INSERT INTO batchItem (batchCode, itemCode, quantityInStock) VALUES (?, ?, ?)";

        try (Connection conn = Database.connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL_INSERT)) {

            pstmt.setInt(1, batchCode);
            pstmt.setInt(2, itemCode);
            pstmt.setInt(3, quantityInStock);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error adding Batch Item to database: " + e.getMessage());
        }
    }

    public Integer getBatchCode() {
        return batchCode;
    }

    public Integer getItemCode() {
        return itemCode;
    }

    public Integer getQuantityInStock() {
        return quantityInStock;
    }
}
