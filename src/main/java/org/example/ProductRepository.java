package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import DatabaseConfiguration.Database;

public class ProductRepository {

    public void addProduct(String productName, Integer productCategoryID) {
        String SQL_INSERT = "INSERT INTO product(productname, productcategoryid) VALUES(?, ?)";

        try (Connection conn = Database.connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL_INSERT)) {

            pstmt.setString(1, productName);
            pstmt.setInt(2, productCategoryID);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error adding Product to database: " + e.getMessage());
        }
    }
}
