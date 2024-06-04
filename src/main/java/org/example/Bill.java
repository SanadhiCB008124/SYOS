package org.example;

import DatabaseConfiguration.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Bill {

    private int billSerialNumber;
    private double netTotal;
    private List<BillItem> billItems;
    private double discount;
    private double cashTendered;
    private double changeAmount;
    private double subTotal;
    private Date dateOfBill;
    private int totalQuantitiesSold;

    public Bill(int billSerialNumber, double netTotal, List<BillItem> billItems, double discount, double cashTendered, double changeAmount, double subTotal, Date dateOfBill, int totalQuantitiesSold) {
        this.billSerialNumber = billSerialNumber;
        this.netTotal = netTotal;
        this.billItems = billItems;
        this.discount = discount;
        this.cashTendered = cashTendered;
        this.changeAmount = changeAmount;
        this.subTotal = subTotal;
        this.dateOfBill = dateOfBill;
        this.totalQuantitiesSold = totalQuantitiesSold;
    }

    public Bill() {
        this.billItems = new ArrayList<>();
    }

    public int getBillSerialNumber() {
        return billSerialNumber;
    }

    public void setBillSerialNumber(int billSerialNumber) {
        this.billSerialNumber = billSerialNumber;
    }

    public double getNetTotal() {
        return netTotal;
    }

    public void setNetTotal(double netTotal) {
        this.netTotal = netTotal;
    }

    public List<BillItem> getBillItems() {
        return billItems;
    }

    public void setBillItems(List<BillItem> billItems) {
        this.billItems = billItems;
    }

    public Date getDateOfBill() {
        return dateOfBill;
    }

    public void setDateOfBill(Date dateOfBill) {
        this.dateOfBill = dateOfBill;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getCashTendered() {
        return cashTendered;
    }

    public void setCashTendered(double cashTendered) {
        this.cashTendered = cashTendered;
    }

    public double getChangeAmount() {
        return changeAmount;
    }

    public void setChangeAmount(double changeAmount) {
        this.changeAmount = changeAmount;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public int getTotalQuantitiesSold() {
        return totalQuantitiesSold;
    }

    public void setTotalQuantitiesSold(int totalQuantitiesSold) {
        this.totalQuantitiesSold = totalQuantitiesSold;
    }

    public List<Bill> allBills() throws SQLException {
        List<Bill> bills = new ArrayList<>();
        String sql = "SELECT * FROM bill";
        String sqlItems = "SELECT * FROM billitem WHERE billserialnumber = ?";

        try (Connection conn = Database.connect();
             Statement statement = conn.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {

            while (rs.next()) {
                int billSerialNumber = rs.getInt("billserialnumber");
                double netTotal = rs.getDouble("nettotal");
                double discount = rs.getDouble("discount");
                double cashTendered = rs.getDouble("cashtendered");
                double changeAmount = rs.getDouble("changeamount");
                double subTotal = rs.getDouble("subtotal");
                Date dateOfBill = rs.getDate("dateofbill");
                int totalQuantitiesSold = rs.getInt("totalquantitiessold");

                List<BillItem> billItems = new ArrayList<>();
                try (PreparedStatement ps = conn.prepareStatement(sqlItems)) {
                    ps.setInt(1, billSerialNumber);
                    try (ResultSet rsItems = ps.executeQuery()) {
                        while (rsItems.next()) {
                            int itemCode = rsItems.getInt("itemcode");
                            int quantity = rsItems.getInt("qtyperitem");
                            double unitPrice = rsItems.getDouble("priceperitem");
                            double totalPrice = rsItems.getDouble("totalamount");

                            BillItem item = new BillItem(itemCode, quantity, unitPrice, totalPrice);
                            billItems.add(item);
                        }
                    }
                }

                Bill bill = new Bill(billSerialNumber, netTotal, billItems, discount, cashTendered, changeAmount, subTotal, dateOfBill, totalQuantitiesSold);
                bills.add(bill);
            }
        }

        return bills;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Bill Serial Number: ").append(billSerialNumber).append("\n");
        sb.append("Date: ").append(dateOfBill).append("\n");
        sb.append("Items:\n");
        sb.append(String.format("%-10s %-20s %-10s %-10s %-10s\n", "ItemCode", "ItemName", "Quantity", "Unit Price", "Total Price"));
        for (BillItem item : billItems) {
            sb.append(String.format("%-10d %-20s %-10d %-10.2f %-10.2f\n", item.getItemCode(), item.getItemName(), item.getQuantity(), item.getUnitPrice(), item.getTotalPrice()));
        }
        sb.append("\n");
        sb.append("Total Quantities sold: ").append(totalQuantitiesSold).append("\n");
        sb.append("Subtotal: ").append(String.format("%.2f", subTotal)).append("\n");
        sb.append("Discount: ").append(String.format("%.2f", discount)).append("\n");
        sb.append("Net Total: ").append(String.format("%.2f", netTotal)).append("\n");
        sb.append("Cash Tendered: ").append(String.format("%.2f", cashTendered)).append("\n");
        sb.append("Change Amount: ").append(String.format("%.2f", changeAmount)).append("\n");
        return sb.toString();
    }
}