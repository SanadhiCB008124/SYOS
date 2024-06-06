package org.example;

import DatabaseConfiguration.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ItemRepository {
    private Item item;


    public void addItemsOnShelf(Integer itemCode, String itemDescription, double unitPrice, Integer quantityOnShelf, Product product) {

        String SQL_INSERT = "INSERT INTO item(itemcode, itemdescription, unitprice, qtyonshelf, productid) VALUES(?, ?, ?, ?, ?)";

        try (Connection conn = Database.connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL_INSERT)) {

            pstmt.setInt(1, itemCode);
            pstmt.setString(2, itemDescription);
            pstmt.setDouble(3, unitPrice);
            pstmt.setInt(4, quantityOnShelf);
            pstmt.setInt(5, product.getProductID());


            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error adding ItemsOnShelf to database: " + e.getMessage());
        }
    }

}
