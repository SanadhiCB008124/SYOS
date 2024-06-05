package org.example;

import DatabaseConfiguration.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class Customer {

    private int customerID;
    private String customerName;

    String customerType;

    public void addCustomer(String customerFirstName, String customerLastName) {
        customerName = customerFirstName + " " + customerLastName;

        String SQL_INSERT = "INSERT INTO Customer (customerName,customertype) VALUES (?,?)";

        try (Connection conn = Database.connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL_INSERT)) {

            pstmt.setString(1, customerName);
            pstmt.setString(2, customerType);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error adding customer to database: " + e.getMessage());
        }
    }



    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerType() {
        return customerType;
    }

    public  abstract void setCustomerType();

}
