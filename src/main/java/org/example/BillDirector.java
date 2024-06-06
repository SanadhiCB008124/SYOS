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
import java.util.Map;
import java.util.HashMap;

public class BillDirector {

    private POSState state;
    private StateContext stateContext;
    private BillBuilder builder;
    private JComboBox<String> paymentStrategyComboBox;
    private Map<String, PaymentStrategy> strategyMap;
    private JFrame frame;
    private JTextField tfBillSerialNumber;
    private JTextField tfItemCode;
    private JTextField tfQuantity;
    private JTextField tfDiscount;
    private JTextField tfCashTendered;
    private JPanel itemsPanel;
    private JScrollPane scrollPane;
    private JTextField tfSubtotal;

    private int totalQuantitiesSold;
    private List<BillItem> billItems = new ArrayList<>();
    private double subTotal = 0.0;

    private List<Command> commands = new ArrayList<>();

    public BillDirector(BillBuilder builder) {
        this.builder = builder;
        this.stateContext=new StateContext();
        this.strategyMap = new HashMap<>();
        strategyMap.put("Cash", new CashPayment());
        strategyMap.put("Credit Card", new CreditCardPayment());
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

        panel.add(new JLabel("Select Payment Method:"));
        paymentStrategyComboBox = new JComboBox<>(strategyMap.keySet().toArray(new String[0]));
        panel.add(paymentStrategyComboBox);

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

        itemsPanel = new JPanel();
        itemsPanel.setLayout(new BoxLayout(itemsPanel, BoxLayout.Y_AXIS));
        scrollPane = new JScrollPane(itemsPanel);

        frame.add(panel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    private void addItem() {
        try {
            int itemCode = Integer.parseInt(tfItemCode.getText());
            int quantity = Integer.parseInt(tfQuantity.getText());
            Item item = fetchItemDetails(itemCode);

            if (item != null) {
                Command addItemCommand = new AddItemCommand(this, item, quantity);
                commands.add(addItemCommand);
                addItemCommand.execute();
            } else {
                JOptionPane.showMessageDialog(frame, "Item with code " + itemCode + " not found.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Invalid input. Please enter valid numbers for item code and quantity.");
        }
    }

    public void addItemToBill(Item item, int quantity) {
        String itemName = item.getProduct().getProductName();
        double totalPrice = item.getUnitPrice() * quantity;
        totalQuantitiesSold += quantity;
        subTotal += totalPrice;

        BillItem billItem = new BillItem(item.getItemCode(), itemName, quantity, item.getUnitPrice(), totalPrice, item);
        billItems.add(billItem);
        addItemToPanel(billItem);

        tfSubtotal.setText(String.format("%.2f", subTotal));
    }

    private void addItemToPanel(BillItem billItem) {
        JPanel itemPanel = new JPanel();
        itemPanel.setLayout(new BoxLayout(itemPanel, BoxLayout.X_AXIS));

        JLabel itemLabel = new JLabel(String.format("Item: %s, Quantity: %d, Unit Price: %.2f, Total Price: %.2f",
                billItem.getItemName(), billItem.getQuantity(), billItem.getUnitPrice(), billItem.getTotalPrice()));

        JButton removeButton = new JButton("Remove");
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeItemFromBill(billItem.getItemCode(), itemPanel);
            }
        });

        itemPanel.add(itemLabel);
        itemPanel.add(removeButton);

        itemsPanel.add(itemPanel);
        itemsPanel.revalidate();
        itemsPanel.repaint();
    }

    private void removeItemFromBill(int itemCode, JPanel itemPanel) {
        BillItem itemToRemove = null;
        for (BillItem item : billItems) {
            if (item.getItemCode() == itemCode) {
                itemToRemove = item;
                break;
            }
        }

        if (itemToRemove != null) {
            Command removeItemCommand=new RemoveItemCommand(this, itemToRemove.getItem(),itemToRemove.getQuantity());
            removeItemCommand.execute();
            commands.add(removeItemCommand);
        } else {
            JOptionPane.showMessageDialog(frame, "Item with code " + itemCode + " not found in the bill.");
        }
    }

    public void removeItemFromBill(int itemCode, int quantity) {
        BillItem itemToRemove = null;
        for (BillItem item : billItems) {
            if (item.getItemCode() == itemCode) {
                itemToRemove = item;
                break;
            }
        }

        if (itemToRemove != null) {
            billItems.remove(itemToRemove);
            totalQuantitiesSold -= quantity;
            subTotal -= itemToRemove.getTotalPrice();

            itemsPanel.removeAll();
            for (BillItem item : billItems) {
                addItemToPanel(item);
            }
            itemsPanel.revalidate();
            itemsPanel.repaint();

            tfSubtotal.setText(String.format("%.2f", subTotal));
        } else {
            JOptionPane.showMessageDialog(frame, "Item with code " + itemCode + " not found in the bill.");
        }
    }

    void finalizeBill() {
        state = new FinalizedState();
        try {
            int billSerialNumber = Integer.parseInt(tfBillSerialNumber.getText());
            double discount = Double.parseDouble(tfDiscount.getText());
            double cashTendered = Double.parseDouble(tfCashTendered.getText());
            double netTotal = subTotal - discount;
            double changeAmount = cashTendered - netTotal;
            String selectedPaymentStrategy = (String) paymentStrategyComboBox.getSelectedItem();
            PaymentStrategy strategy = strategyMap.get(selectedPaymentStrategy);
            PaymentContext paymentContext = new PaymentContext(strategy);

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
            builder.addPaymentMethod(selectedPaymentStrategy);

            Bill bill = builder.getBill();
            paymentContext.executeStrategy(bill);
            saveBill(bill);

            tfSubtotal.setText(String.format("Subtotal:",
                    subTotal));
            JOptionPane.showMessageDialog(frame,"Bill added successfully");

            stateContext.processBill(this);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Invalid input. Please enter valid numbers for discount and cash tendered.");
        }
    }

    public Item fetchItemDetails(int itemCode) {
        Item item = null;
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

                    item = new Item(itemCode, itemDescription, unitPrice, product);
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

        state=new FinalizedState();

        String billSQL = "INSERT INTO bill (billSerialNumber, dateOfBill, subTotal, discount, netTotal, cashTendered, changeAmount, totalQuantitiesSold,paymentMethod) VALUES (?, ?, ?, ?, ?, ?, ?,?, ?)";
        String billItemSQL = "INSERT INTO billItem (billSerialNumber, itemCode, qtyperitem, priceperitem, totalamount) VALUES (?, ?, ?, ?, ?)";
        String updateItemQty = "UPDATE item SET qtyonshelf = qtyonshelf - ? WHERE itemcode = ?";

        try (Connection conn = Database.connect();
             PreparedStatement billStmt = conn.prepareStatement(billSQL);
             PreparedStatement billItemStmt = conn.prepareStatement(billItemSQL);
             PreparedStatement updateItemStmt = conn.prepareStatement(updateItemQty)) {

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
            billStmt.setString(9, bill.getPaymentMethod());
            billStmt.executeUpdate();

            // Insert bill items
            for (BillItem item : bill.getBillItems()) {
                billItemStmt.setInt(1, bill.getBillSerialNumber());
                billItemStmt.setInt(2, item.getItemCode());
                billItemStmt.setInt(3, item.getQuantity());
                billItemStmt.setDouble(4, item.getUnitPrice());
                billItemStmt.setDouble(5, item.getTotalPrice());
                billItemStmt.executeUpdate();

                // Update item quantity on shelf after each bill item is inserted
                updateItemStmt.setInt(1, item.getQuantity());
                updateItemStmt.setInt(2, item.getItemCode());
                updateItemStmt.executeUpdate();
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

    public void checkState() {
        stateContext.checkState();
    }


    public static void main(String[] args) {
        BillBuilder builder = new ConcreteBillBuilder();
        BillDirector billDirector=new BillDirector(builder);
        billDirector.checkState();

    }

}
