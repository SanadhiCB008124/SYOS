package org.example.View;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.sql.Date;

public class batchItemView extends JFrame {

    private JTextField itemCode = new JTextField(20);
    private JTextField batchCode = new JTextField(10);
    private JTextField quantityInStock = new JTextField(10);
    private JButton addBatchItem = new JButton("Add");
    private JTextField newBatchItem = new JTextField(50);

    private JTextField expiryDate = new JFormattedTextField("yyyy-MM-dd");
    private JTextField manufactureDate = new JFormattedTextField("yyyy-MM-dd");

    private JTextField batchDate = new JFormattedTextField("yyyy-MM-dd");



    public batchItemView() {
        JPanel batchItemPanel = new JPanel();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 200);
        this.setResizable(true);

        batchItemPanel.add(new JLabel("Item Code:"));
        batchItemPanel.add(itemCode);
        batchItemPanel.add(new JLabel("Batch Code:"));
        batchItemPanel.add(batchCode);
        batchItemPanel.add(new JLabel("Quantity in Stock:"));
        batchItemPanel.add(quantityInStock);
        batchItemPanel.add(new JLabel("Expiry Date:"));
        batchItemPanel.add(expiryDate);
        batchItemPanel.add(new JLabel("Manufacture Date:"));
        batchItemPanel.add(manufactureDate);
        batchItemPanel.add(new JLabel("Batch Date:"));
        batchItemPanel.add(batchDate);


        batchItemPanel.add(addBatchItem);
        batchItemPanel.add(new JLabel("Batch Item:"));

        this.add(batchItemPanel);
    }

    public String getItemCode() {
        return itemCode.getText();
    }

    public Integer getBatchCode() {
        return Integer.parseInt(batchCode.getText());
    }

    public JTextField getQuantityInStock() {
        return quantityInStock;
    }

    public Date getExpiryDate() {
        return  Date.valueOf(expiryDate.getText());
    }

    public Date getManufactureDate(){
        return  Date.valueOf(manufactureDate.getText());
    }

    public Date getBatchDate(){
        return  Date.valueOf(batchDate.getText());
    }

    public void displaySuccessMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public void displayError(String errorMessage) {
        JOptionPane.showMessageDialog(this, errorMessage);
    }

    public void addBatchItemListener(ActionListener listenForAddButton) {
        addBatchItem.addActionListener(listenForAddButton);
    }


}
