package org.example;

import DatabaseConfiguration.Database;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BillDirector{
    private BillBuilder builder;

    private JFrame frame;
    private JTextField tfBillSerialNumber;
    private JTextField tfItemCode;
    private JTextField tfQuantity;
    private JTextField tfDiscount;
    private JTextField tfCashTendered;
    private JTextArea taBillDetails;
    private JTextField tfSubtotal;

    private int totalQuantitiesSold;
    private List<BillItem> billItems = new ArrayList<>();
    private double subTotal = 0.0;


    public BillDirector(BillBuilder builder) {
        this.builder = builder;

        createGUI();
    }

    private void createGUI() {
        frame = new JFrame("POS");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setResizable(true);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panel.add(new JLabel("Enter Bill Serial Number:"));
        tfBillSerialNumber = new JTextField(20);
        panel.add(tfBillSerialNumber);

        panel.add(new JLabel("Enter Item Code:"));
        tfItemCode = new JTextField(20);
        panel.add(tfItemCode);

        panel.add(new JLabel("Enter Quantity:"));
        tfQuantity = new JTextField(20);
        panel.add(tfQuantity);

        JButton addItemButton = new JButton("Add Item");
        panel.add(addItemButton);
        addItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addItem();
            }
        });

        panel.add(new JLabel("Enter Discount:"));
        tfDiscount = new JTextField(20);
        panel.add(tfDiscount);

        panel.add(new JLabel("Enter Cash Tendered:"));
        tfCashTendered = new JTextField(20);
        panel.add(tfCashTendered);

        JButton finalizeButton = new JButton("Finalize Bill");
        panel.add(finalizeButton);
        finalizeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                finalizeBill();
            }
        });

        tfSubtotal = new JTextField(20);
        tfSubtotal.setEditable(false);
        panel.add(new JLabel("Subtotal:"));
        panel.add(tfSubtotal);

        taBillDetails = new JTextArea(10, 40);
        taBillDetails.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(taBillDetails);

        frame.add(panel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    private void addItem() {
        try {
            int itemCode = Integer.parseInt(tfItemCode.getText());
            int quantity = Integer.parseInt(tfQuantity.getText());

            Items item = fetchItemDetails(itemCode);
            if (item != null) {
                String itemName = item.getProduct().getProductName();
                double totalPrice = item.getUnitPrice() * quantity;
                totalQuantitiesSold += quantity;
                subTotal += totalPrice;



                BillItem billItem = new BillItem(item.getItemCode(), itemName, quantity, item.getUnitPrice(), totalPrice, item);
                billItems.add(billItem);
                taBillDetails.append(String.format("Item: %s, Quantity: %d, Unit Price: %.2f, Total Price: %.2f%n ",
                        itemName, quantity, item.getUnitPrice(), totalPrice));


                tfSubtotal.setText(String.format("%.2f", subTotal));

            } else {
                JOptionPane.showMessageDialog(frame, "Item with code " + itemCode + " not found.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Invalid input. Please enter valid numbers for item code and quantity.");
        }
    }

    private void finalizeBill() {
        try {
            int billSerialNumber = Integer.parseInt(tfBillSerialNumber.getText());
            double discount = Double.parseDouble(tfDiscount.getText());
            double cashTendered = Double.parseDouble(tfCashTendered.getText());
            double netTotal = subTotal - discount;
            double changeAmount = cashTendered - netTotal;



            Date dateOfBill = new Date();

            builder.addSerialNumber(billSerialNumber);
            builder.addBillItems(billItems);
            builder.addSubtotal(subTotal);
            builder.addDiscount(discount);
            builder.addNetTotal(netTotal);
            builder.addCashTendered(cashTendered);
            builder.addChangeAmount(changeAmount);
            builder.addDateOfBill(dateOfBill);
            builder.addTotalQuantitiesSold(totalQuantitiesSold);

            Bill bill = builder.getBill();
            saveBill(bill);

            taBillDetails.append(String.format("%nSubtotal: %.2f%nDiscount: %.2f%nNet Total: %.2f%nCash Tendered: %.2f%nChange Amount: %.2f%n",
                    subTotal, discount, netTotal, cashTendered, changeAmount));


        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Invalid input. Please enter valid numbers for discount and cash tendered.");
        }
    }

    public Items fetchItemDetails(int itemCode) {
        Items item = null;
        String query = "SELECT itemcode, itemdescription, unitprice, productid FROM item WHERE itemcode = ?";

        try (Connection conn = Database.connect();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, itemCode);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Integer productId = rs.getInt("productid");
                    String itemDescription = rs.getString("itemdescription");
                    double unitPrice = rs.getDouble("unitprice");

                    Product product = fetchProductDetails(productId);

                    item = new Items(itemCode, itemDescription, unitPrice, product);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    private Product fetchProductDetails(int productId) {
        Product product = null;
        String query = "SELECT productid, productname FROM product WHERE productid = ?";
        try (Connection conn = Database.connect();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, productId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String productName = rs.getString("productname");
                    product = new Product(productId, productName);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    public void saveBill(Bill bill) {
        String billSQL = "INSERT INTO bill (billSerialNumber, dateOfBill, subTotal, discount, netTotal, cashTendered, changeAmount, totalQuantitiesSold) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        String billItemSQL = "INSERT INTO billItem (billSerialNumber, itemCode, qtyperitem, priceperitem, totalamount) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = Database.connect();
             PreparedStatement billStmt = conn.prepareStatement(billSQL);
             PreparedStatement billItemStmt = conn.prepareStatement(billItemSQL)) {

            conn.setAutoCommit(false);

            // Insert bill
            billStmt.setInt(1, bill.getBillSerialNumber());
            billStmt.setDate(2, new java.sql.Date(bill.getDateOfBill().getTime()));
            billStmt.setDouble(3, bill.getSubTotal());
            billStmt.setDouble(4, bill.getDiscount());
            billStmt.setDouble(5, bill.getNetTotal());
            billStmt.setDouble(6, bill.getCashTendered());
            billStmt.setDouble(7, bill.getChangeAmount());
            billStmt.setInt(8,bill.getTotalQuantitiesSold());
            billStmt.executeUpdate();

            // Insert bill items
            for (BillItem item : bill.getBillItems()) {
                billItemStmt.setInt(1, bill.getBillSerialNumber());
                billItemStmt.setInt(2, item.getItemCode());
                billItemStmt.setInt(3, item.getQuantity());
                billItemStmt.setDouble(4, item.getUnitPrice());
                billItemStmt.setDouble(5, item.getTotalPrice());
                billItemStmt.executeUpdate();
            }

            conn.commit();


        } catch (SQLException e) {
            e.printStackTrace();
            try {
                Connection conn = Database.connect();
                conn.rollback();
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        BillBuilder builder = new ConcreteBillBuilder();

        new BillDirector(builder);


    }
}
