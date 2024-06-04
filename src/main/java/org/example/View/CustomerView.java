package org.example.View;

import javax.swing.*;
import java.awt.event.ActionListener;

public class CustomerView extends JFrame {

    private JTextField customerFirstName = new JTextField(20);
    private JTextField customerLastName = new JTextField(20);
    private JButton addCustomer = new JButton("Add");
    private JTextField customerName = new JTextField(50);

    public CustomerView() {
        JPanel customerPanel = new JPanel();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 200);
        customerPanel.add(new JLabel("First Name:"));
        customerPanel.add(customerFirstName);
        customerPanel.add(new JLabel("Last Name:"));
        customerPanel.add(customerLastName);
        customerPanel.add(addCustomer);
        customerPanel.add(new JLabel("Customer Name:"));
        customerPanel.add(customerName);

        this.add(customerPanel);
    }

    public String getCustomerFirstName() {
        return customerFirstName.getText();
    }

    public String getCustomerLastName() {
        return customerLastName.getText();
    }

    public void setCustomerName(String name) {
        customerName.setText(name);
    }

    public void addCustomerListener(ActionListener listener) {
        addCustomer.addActionListener(listener);
    }

    public void displaySuccessMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public void displayError(String errorMessage) {
        JOptionPane.showMessageDialog(this, errorMessage);
    }
}
