package org.example.Reports;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import DatabaseConfiguration.Database;
import org.example.*;

public class SalesReport extends Report {
    private List<Bill> bills = loadAllBills();

    public List<Bill> loadAllBills() {
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
                String paymentStrategy = rs.getString("paymentmethod");

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

                Bill bill = new Bill(billSerialNumber, netTotal, billItems, discount, cashTendered, changeAmount, subTotal, dateOfBill, totalQuantitiesSold, paymentStrategy);
                bills.add(bill);


            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return bills;
    }

    @Override
    protected void getData() {
        System.out.println("Getting data for Sales Report");

    }

    @Override
    protected void createReport() {
        System.out.println("Creating Sales Report");
        double totalRevenue=0.00;


        SalesPerDayCriteria salesPerDayCriteria = new SalesPerDayCriteria();
        List<Bill> filteredBills = salesPerDayCriteria.FilterBillsByDay(bills);
        salesPerDayCriteria.FilterBillsByDay(bills);

        for (Bill bill : filteredBills) {
            System.out.println(bill);
            totalRevenue+=bill.getNetTotal();
        }

        System.out.println("Total Revenue"+ totalRevenue);
    }
}
