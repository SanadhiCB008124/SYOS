package org.example;

import DatabaseConfiguration.Database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class batchItem {

    private List<Observer> observers = new ArrayList<Observer>();

    private Integer batchItemId;
    private Integer batchCode;
    private Integer itemCode;
    private Integer quantityInStock;

    private java.sql.Date expiryDate;
    private java.sql.Date manufactureDate;

    private java.sql.Date batchDate;

    public batchItem(Integer batchItemId, Integer batchCode, Integer itemCode, Integer quantityInStock, Date expiryDate, Date manufactureDate, Date batchDate) {
        this.batchItemId = batchItemId;
        this.batchCode = batchCode;
        this.itemCode = itemCode;
        this.quantityInStock = quantityInStock;
        this.expiryDate = expiryDate;
        this.manufactureDate = manufactureDate;
        this.batchDate = batchDate;
    }

    public batchItem() {

    }


    public void addBatchItems(Integer batchCode, Integer itemCode, Integer quantityInStock, Date manufactureDate, Date expiryDate, Date batchDate) {

        String SQL_INSERT = "INSERT INTO batchItem (batchCode, itemCode, quantityInStock, manufacturedate, expirydate, batchdate) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = Database.connect();
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

    public Integer getBatchCode() {
        return batchCode;
    }

    public Date getBatchDate() {
        return batchDate;
    }

    public void setBatchDate(Date batchDate) {
        this.batchDate = batchDate;
    }

    public Integer getItemCode() {
        return itemCode;
    }

    public Integer getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(Integer quantityInStock) {
        this.quantityInStock = quantityInStock;
        notifyAllObservers();
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Date getManufactureDate() {
        return manufactureDate;
    }

    public void setManufactureDate(Date manufactureDate) {
        this.manufactureDate = manufactureDate;
    }

    public void checkLowStock(Integer quantityInStock) {
        if (quantityInStock < 50) {
            notifyAllObservers();
        }
    }

    public void attach(Observer observer) {
        observers.add(observer);
    }

    public void notifyAllObservers() {
        for (Observer observer : observers) {
            observer.update();
            observer.lowStockAlert();
        }
    }
}
