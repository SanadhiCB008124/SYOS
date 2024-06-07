package org.example;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Stock {

    private List<Observer> observers = new ArrayList<Observer>();
    private Integer stockItemId;

    private String itemName;
    private Integer batchCode;
    private Integer itemCode;
    private Integer quantityInStock=0;

    private java.sql.Date expiryDate;
    private java.sql.Date manufactureDate;

    private java.sql.Date batchDate;

    private StockItemRepository stockItemRepository;

    public Stock(Integer stockItemId, Integer batchCode, String itemName,Integer itemCode, Integer quantityInStock, Date expiryDate, Date manufactureDate, Date batchDate) {
        this.stockItemId = stockItemId;
        this.itemName=itemName;
        this.batchCode = batchCode;
        this.itemCode = itemCode;
        this.quantityInStock = quantityInStock;
        this.expiryDate = expiryDate;
        this.manufactureDate = manufactureDate;
        this.batchDate = batchDate;
    }

    public Stock() {}

    public Stock(Integer stockItemId, String itemName, int itemCode, int batchCode, int quantityInStock, Date expiryDate, Date manufactureDate, Date batchDate) {
        this.stockItemId = stockItemId;
        this.itemName=itemName;
        this.batchCode = batchCode;
        this.itemCode = itemCode;
        this.quantityInStock = quantityInStock;
        this.expiryDate = expiryDate;
        this.manufactureDate = manufactureDate;
        this.batchDate = batchDate;

    }


    public StockItemRepository getBatchItemRepository() {
        return stockItemRepository;
    }

    public void setBatchItemRepository(StockItemRepository stockItemRepository) {
        this.stockItemRepository = stockItemRepository;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
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

    public void checkLowStock() {
        if (getQuantityInStock() != null && getQuantityInStock() < 50) {
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
