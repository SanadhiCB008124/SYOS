package org.example;

public class BillItem {

    private int billItemID;
    private String itemName;
    private String itemDescription;
    private Integer itemCode;
    private int quantity;
    private double unitPrice;
    private double totalPrice;
    private Items item;

    public BillItem() {
    }

    public BillItem(Integer itemCode,String itemName, int quantity, double unitPrice, double totalPrice, Items item) {
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.totalPrice = totalPrice;
        this.item = item;
    }

    public BillItem(Integer itemCode, int quantity, double unitPrice, double totalPrice) {
        this.itemCode = itemCode;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.totalPrice = totalPrice;
    }

    public int getBillItemID() {
        return billItemID;
    }

    public void setBillItemID(int billItemID) {
        this.billItemID = billItemID;
    }


    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Integer getItemCode() {
        return itemCode;
    }

    public void setItemCode(Integer itemCode) {
        this.itemCode = itemCode;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        setTotalPrice(this.unitPrice * this.quantity);

    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
        setTotalPrice(this.unitPrice * this.quantity);
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public Items getItem() {
        return item;
    }

    public void setItem(Items item) {
        this.item = item;
    }
}
