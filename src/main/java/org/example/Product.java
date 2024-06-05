package org.example;

import DatabaseConfiguration.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Product{
    private int productID;
    private String productName;
    private int productCategoryID;

    public Product(int productID, String productName, int productCategoryID) {
        this.productID = productID;
        this.productName = productName;
        this.productCategoryID = productCategoryID;
    }

    public Product() {

    }

    public Product(int productID, String productName) {
        this.productID = productID;
        this.productName = productName;
    }

    public Product(int productID) {
        this.productID = productID;

    }


    public void addProduct(String productName, Integer productCategoryID) {
        this.productName = productName;
        this.productCategoryID = productCategoryID;

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


    public String getProductName() {

        return productName;
    }

    public int getProductID() {

        return productID;
    }

    public  int getProductCategoryID(){
        return productCategoryID;
    }


}
