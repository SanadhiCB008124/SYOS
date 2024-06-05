package org.example;


import DatabaseConfiguration.Database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class batchItem {

    private List<Observer> observers=new ArrayList<Observer>();

    private Integer batchItemId;
    private Integer batchCode;
    private Integer itemCode;
    private Integer quantityInStock;

    private Date expiryDate;
    private Date manufactureDate;

    public batchItem(Integer batchItemId, Integer batchCode, Integer itemCode, Integer quantityInStock, Date expiryDate,Date manufactureDate) {
        this.batchItemId = batchItemId;
        this.batchCode = batchCode;
        this.itemCode = itemCode;
        this.quantityInStock = quantityInStock;
        this.expiryDate = new Date(expiryDate.getTime());
        this.manufactureDate = new Date(manufactureDate.getTime());

    }

    public batchItem() {

    }

    public void addBatchItems(Integer batchCode, Integer itemCode, Integer quantityInStock, Date manufactureDate, Date expiryDate) {

        String SQL_INSERT = "INSERT INTO batchItem (batchCode, itemCode, quantityInStock,manufacturedate,expirydate) VALUES (?, ?,?,?, ?)";

        try (Connection conn = Database.connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL_INSERT)) {

            pstmt.setInt(1, batchCode);
            pstmt.setInt(2, itemCode);
            pstmt.setInt(3, quantityInStock);
            pstmt.setDate(4, new java.sql.Date(manufactureDate.getTime()));
            pstmt.setDate(5, new java.sql.Date(expiryDate.getTime()));


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

    public void checkLowStock(Integer quantityInStock){
        if(quantityInStock<50){
            notifyAllObservers();
        }
    }

    public void attach(Observer observer){
        observers.add(observer);
    }

    public void notifyAllObservers(){
        for (Observer observer : observers) {
            observer.update();
            observer.lowStockAlert();

        }
    }
}
