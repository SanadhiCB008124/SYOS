package org.example;

import DatabaseConfiguration.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CustomerModel {

    private int customerID;
    private String customerName;

    public void addCustomer(String customerFirstName, String customerLastName) {
        customerName = customerFirstName + " " + customerLastName;

        String SQL_INSERT = "INSERT INTO Customer (customerName) VALUES (?)";

        try (Connection conn = Database.connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL_INSERT)) {

            pstmt.setString(1, customerName);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error adding customer to database: " + e.getMessage());
        }
    }

    public String getCustomerName() {
        return customerName;
    }
}
