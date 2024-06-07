package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Map;

public class BillInterfaceService {
    private JComboBox<String> paymentStrategyComboBox;
    private JFrame frame;
    private JTextField tfBillSerialNumber;
    private JTextField tfItemCode;
    private JTextField tfQuantity;
    private JTextField tfDiscount;
    private JTextField tfCashTendered;
    private JPanel itemsPanel;
    private JScrollPane scrollPane;
    private JTextField tfSubtotal;
    private Map<String, PaymentStrategy> strategyMap;
    private JButton addItemButton;
    private JButton finalizeButton;

    private  JTextField customerName;

    public BillInterfaceService(Map<String, PaymentStrategy> strategyMap) {
        this.strategyMap=strategyMap;
        createGUI(strategyMap);
    }

    private void createGUI(Map<String, PaymentStrategy> strategyMap) {
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

        addItemButton = new JButton("Add Item");
        panel.add(addItemButton);

        panel.add(new JLabel("Enter Customer Name:"));
        customerName = new JTextField(20);
        panel.add(customerName);

        panel.add(new JLabel("Select Payment Method:"));
        paymentStrategyComboBox = new JComboBox<>(this.strategyMap.keySet().toArray(new String[0]));
        panel.add(paymentStrategyComboBox);

        panel.add(new JLabel("Enter Discount:"));
        tfDiscount = new JTextField(20);
        panel.add(tfDiscount);

        panel.add(new JLabel("Enter Cash Tendered:"));
        tfCashTendered = new JTextField(20);
        panel.add(tfCashTendered);

        finalizeButton = new JButton("Finalize Bill");
        panel.add(finalizeButton);

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

    public JComboBox<String> getPaymentStrategyComboBox() {
        return paymentStrategyComboBox;
    }

    public void setPaymentStrategyComboBox(JComboBox<String> paymentStrategyComboBox) {
        this.paymentStrategyComboBox = paymentStrategyComboBox;
    }

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public JTextField getTfBillSerialNumber() {
        return tfBillSerialNumber;
    }

    public void setTfBillSerialNumber(JTextField tfBillSerialNumber) {
        this.tfBillSerialNumber = tfBillSerialNumber;
    }

    public JTextField getTfItemCode() {
        return tfItemCode;
    }

    public void setTfItemCode(JTextField tfItemCode) {
        this.tfItemCode = tfItemCode;
    }

    public JTextField getTfQuantity() {
        return tfQuantity;
    }

    public void setTfQuantity(JTextField tfQuantity) {
        this.tfQuantity = tfQuantity;
    }


    public JTextField getTfDiscount() {
        return tfDiscount;
    }

    public void setTfDiscount(JTextField tfDiscount) {
        this.tfDiscount = tfDiscount;
    }

    public JTextField getTfCashTendered() {
        return tfCashTendered;
    }

    public void setTfCashTendered(JTextField tfCashTendered) {
        this.tfCashTendered = tfCashTendered;
    }

    public JPanel getItemsPanel() {
        return itemsPanel;
    }

    public void setItemsPanel(JPanel itemsPanel) {
        this.itemsPanel = itemsPanel;
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    public void setScrollPane(JScrollPane scrollPane) {
        this.scrollPane = scrollPane;
    }

    public JTextField getTfSubtotal() {
        return tfSubtotal;
    }

    public void setTfSubtotal(JTextField tfSubtotal) {
        this.tfSubtotal = tfSubtotal;
    }

    public Map<String, PaymentStrategy> getStrategyMap() {
        return strategyMap;
    }

    public void setStrategyMap(Map<String, PaymentStrategy> strategyMap) {
        this.strategyMap = strategyMap;
    }
    public void addItemListener(ActionListener listener) {
        addItemButton.addActionListener(listener);
    }

    public void finalizeBillListener(ActionListener listener) {
        finalizeButton.addActionListener(listener);
    }

    public JTextField getCustomerName() {
        return customerName;
    }

    public void setCustomerName(JTextField customerName) {
        this.customerName = customerName;
    }
}
