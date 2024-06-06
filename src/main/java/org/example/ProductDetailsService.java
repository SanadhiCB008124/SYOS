package org.example;

import DatabaseConfiguration.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductDetailsService {

    public Product fetchProductDetails(int productId) {
        Product product = null;
        String query = "SELECT productid, productname FROM product WHERE productid = ?";
        try (Connection conn = Database.connect();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, productId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String productName = rs.getString("productname");
                    product = new Product(productId, productName);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }
}
