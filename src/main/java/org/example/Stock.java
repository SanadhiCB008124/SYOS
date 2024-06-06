package org.example;

import DatabaseConfiguration.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;

public class Stock {

    private Integer batchCode;
    private Date batchDate;

    public Stock(int code, Date date) {
        this.batchCode = code;
        this.batchDate = date;
    }

    public Stock() {

    }

    public void addStockRecord(Integer batchCode, Date batchDate) {
        this.batchCode = batchCode;
        this.batchDate = batchDate;

        String SQL_INSERT = "INSERT INTO stock (batchCode, batchDate) VALUES (?, ?)";

        try (Connection conn = Database.connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL_INSERT)) {

            pstmt.setInt(1, batchCode);
            pstmt.setDate(2, batchDate);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error adding store records to database: " + e.getMessage());
        }
    }

    public Integer getBatchCode() {
        return batchCode;
    }

    public Date getBatchDate() {
        return batchDate;
    }

    public Object getSelectedItem() {
        return batchCode;
    }
}

