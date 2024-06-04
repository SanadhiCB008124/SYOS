package org.example.View;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.sql.Date;

public class stockView extends JFrame {

    private JTextField batchCode = new JTextField(20);
    private JTextField batchDate = new JFormattedTextField("yyyy-MM-dd");
    private JButton addStockRecord = new JButton("Add");
    private JTextField record = new JTextField(50);

    public stockView() {
        JPanel storePanel = new JPanel();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 200);
        storePanel.add(new JLabel("Batch Code:"));
        storePanel.add(batchCode);
        storePanel.add(new JLabel("Batch Date:"));
        storePanel.add(batchDate);
        storePanel.add(addStockRecord);
        storePanel.add(new JLabel("stock record:"));
        storePanel.add(record);

        this.add(storePanel);
    }

    public Integer getBatchCode() {
        return Integer.parseInt(batchCode.getText());
    }


    public Date getBatchDate() {
        return  Date.valueOf(batchDate.getText());
    }


    public void addStockListener(ActionListener Listener) {
        addStockRecord.addActionListener(Listener);
    }



    public void displayError(String errorMessage) {
        JOptionPane.showMessageDialog(this, errorMessage);
    }

    public void setStock(Integer batchCode, Date batchDate) {
        record.setText("Batch Code: " + batchCode + ", Batch Date: " + batchDate);
    }

    public void displaySuccessMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

}
