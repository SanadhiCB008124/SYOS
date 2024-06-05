package org.example;
import DatabaseConfiguration.Database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Items {

    private List<Observer> observers=new ArrayList<Observer>();

    private static final int ShelfSize = 20;

    private Integer itemCode;
    private String itemDescription;
    private double unitPrice;
    private Date expiryDate;
    private Date manufactureDate;
    private Integer quantityOnShelf;
    private Product product;
    private double discount;


    public Items(Integer itemCode, String itemDescription, double unitPrice,Product product,double discount) {
        this.itemCode = itemCode;
        this.itemDescription = itemDescription;
        this.unitPrice = unitPrice;
        this.product=product;
        this.discount=discount;
    }

    public Items() {
    }

    public Items(Integer itemCode, String itemDescription, double unitPrice) {
        this.itemCode=itemCode;
        this.itemDescription=itemDescription;
        this.unitPrice=unitPrice;
    }

    public Items(Integer itemCode, String itemDescription, double unitPrice, Product product) {
        this.itemCode=itemCode;
        this.itemDescription=itemDescription;
        this.unitPrice=unitPrice;
        this.product=product;

    }


    public Integer getItemCode() {
        return itemCode;
    }

    public void setItemCode(Integer itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public String getItemName(){
       return product.getProductName();
    }

    public void setUnitPrice(double unitPrice) {

        this.unitPrice = unitPrice;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
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

    public Integer getQuantityOnShelf() {
        return quantityOnShelf;
    }

    public void setQuantityOnShelf(Integer quantityOnShelf) {
        this.quantityOnShelf=quantityOnShelf;
        notifyAllObservers();
    }

    

    public void checkLowStock(Integer quantityOnShelf){
        if(quantityOnShelf<10){
            notifyAllObservers();
        }
    }

    public void addItemsOnShelf(Integer itemCode, String itemDescription, double unitPrice, Integer quantityOnShelf, Product product, java.util.Date expiryDate, java.util.Date manufactureDate, DiscountStrategy discountStrategy ) {
        if (quantityOnShelf > ShelfSize) {
            throw new IllegalArgumentException("Quantity on shelf cannot be greater than " + ShelfSize);
        }
        this.itemCode = itemCode;
        this.itemDescription = itemDescription;
        this.unitPrice = unitPrice;
        this.quantityOnShelf = quantityOnShelf;
        this.product = product;
        this.expiryDate = new Date(expiryDate.getTime());
        this.manufactureDate = new Date(manufactureDate.getTime());
        this.discount = discountStrategy.applyDiscount(unitPrice);

        String SQL_INSERT = "INSERT INTO item(itemcode, itemdescription, unitprice, qtyonshelf, productid, expirydate, manufacturedate,discount) VALUES(?, ?, ?, ?, ?, ?,?,?)";

        try (Connection conn = Database.connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL_INSERT)) {

            pstmt.setInt(1, itemCode);
            pstmt.setString(2, itemDescription);
            pstmt.setDouble(3, unitPrice);
            pstmt.setInt(4, quantityOnShelf);
            pstmt.setInt(5, product.getProductID());
            pstmt.setDate(6, new java.sql.Date(expiryDate.getTime()));
            pstmt.setDate(7, new java.sql.Date(manufactureDate.getTime()));
            pstmt.setDouble(8,discount);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error adding ItemsOnShelf to database: " + e.getMessage());
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

