package org.example.View;
import org.example.Product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Properties;

public class ItemsOnShelfView extends JFrame {

    private JTextField itemcode = new JTextField(20);
    private JTextField itemdescription = new JTextField(20);
    private JTextField unitprice = new JTextField(20);

    private JTextField qtyonshelf = new JTextField(20);
    private JComboBox<String> productid = new JComboBox<>();
    private JButton addItemsOnShelf = new JButton("Add");

    private JTextField expiryDate = new JFormattedTextField("yyyy-MM-dd");
    private JTextField manufactureDate = new JFormattedTextField("yyyy-MM-dd");


    public ItemsOnShelfView() {
        JPanel itemsOnShelfPanel = new JPanel(new GridLayout(0, 2));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 400);

        itemsOnShelfPanel.add(new JLabel("Item Code:"));
        itemsOnShelfPanel.add(itemcode);
        itemsOnShelfPanel.add(new JLabel("Item Description:"));
        itemsOnShelfPanel.add(itemdescription);
        itemsOnShelfPanel.add(new JLabel("Unit Price:"));
        itemsOnShelfPanel.add(unitprice);
        itemsOnShelfPanel.add(new JLabel("Quantity On Shelf:"));
        itemsOnShelfPanel.add(qtyonshelf);
        itemsOnShelfPanel.add(new JLabel("Product ID:"));
        itemsOnShelfPanel.add(productid);
        itemsOnShelfPanel.add(new JLabel("Expiry Date:"));
        itemsOnShelfPanel.add(expiryDate);
        itemsOnShelfPanel.add(new JLabel("Manufacture Date:"));
        itemsOnShelfPanel.add(manufactureDate);

        itemsOnShelfPanel.add(addItemsOnShelf);
        this.add(itemsOnShelfPanel);
    }

    public String getItemCode() {
        return itemcode.getText();
    }

    public String getItemDescription() {
        return itemdescription.getText();
    }

    public String getUnitPrice() {
        return unitprice.getText();
    }

    public String getQuantityOnShelf() {
        return qtyonshelf.getText();
    }

    public String getProductID() {
        return productid.getSelectedItem().toString().split(" - ")[0];
    }

    public void setProducts(List<Product> products) {
        for (Product product : products) {
            productid.addItem(product.getProductID() + " - " + product.getProductName());
        }
    }

    public Date getExpiryDate() {
        return  Date.valueOf(expiryDate.getText());
    }

    public Date getManufactureDate() {
        return  Date.valueOf(manufactureDate.getText());
    }


    public void addItemsOnShelfListener(ActionListener listener) {
        addItemsOnShelf.addActionListener(listener);
    }

    public void displayErrorMessage(String errorMessage) {
        JOptionPane.showMessageDialog(this, errorMessage);
    }

    public void displaySuccessMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }


}

