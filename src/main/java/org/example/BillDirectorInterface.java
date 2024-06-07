package org.example;

import javax.swing.*;

public interface BillDirectorInterface {
    void addItem();

    void addItemToBill(Item item, int quantity);

     void addItemToPanel(BillItem billItem);

    void removeItemFromBill(int itemCode, JPanel itemPanel);

    void removeItemFromBill(int itemCode, int quantity);

     void finalizeBill();
}
