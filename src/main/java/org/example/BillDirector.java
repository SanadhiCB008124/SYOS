package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class BillDirector {
    private StateContext stateContext;
    private BillBuilder builder;
    private Map<String, PaymentStrategy> strategyMap;
    private int totalQuantitiesSold;
    private List<BillItem> billItems = new ArrayList<>();
    private double subTotal = 0.0;
    private List<Command> commands = new ArrayList<>();
    ItemDetailsService itemDetailsService;
    BillSaveService billSaveService;

    BillInterfaceService billInterfaceService;

    public BillDirector(BillBuilder builder, ItemDetailsService itemDetailsService,BillSaveService billSaveService,BillInterfaceService billInterfaceService) {
        this.builder = builder;
        this.stateContext=new StateContext();
        this.itemDetailsService=itemDetailsService;
        this.billSaveService=billSaveService;
        this.billInterfaceService=billInterfaceService;
        this.strategyMap = new HashMap<>();
        strategyMap.put("Cash", new CashPayment());
        strategyMap.put("Credit Card", new CreditCardPayment());

        billInterfaceService.addItemListener(e->addItem());
        billInterfaceService.finalizeBillListener(e->finalizeBill());
    }


    private void addItem() {
        try {
            int itemCode = Integer.parseInt(billInterfaceService.getTfItemCode().getText());
            int quantity = Integer.parseInt(billInterfaceService.getTfQuantity().getText());
            Item item = itemDetailsService.fetchItemDetails(itemCode);

            if (item != null) {
                Command addItemCommand = new AddItemCommand(this, item, quantity);
                commands.add(addItemCommand);
                addItemCommand.execute();
            } else {
                JOptionPane.showMessageDialog(billInterfaceService.getFrame(), "Item with code " + itemCode + " not found.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(billInterfaceService.getFrame(), "Invalid input. Please enter valid numbers for item code and quantity.");
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

        billInterfaceService.getTfSubtotal().setText(String.format("%.2f", subTotal));
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

        billInterfaceService.getItemsPanel().add(itemPanel);
        billInterfaceService.getItemsPanel().revalidate();
        billInterfaceService.getItemsPanel().repaint();
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
            JOptionPane.showMessageDialog(billInterfaceService.getFrame(), "Item with code " + itemCode + " not found in the bill.");
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

            billInterfaceService.getItemsPanel().removeAll();
            for (BillItem item : billItems) {
                addItemToPanel(item);
            }
            billInterfaceService.getItemsPanel().revalidate();
            billInterfaceService.getItemsPanel().repaint();

            billInterfaceService.getTfSubtotal().setText(String.format("%.2f", subTotal));
        } else {
            JOptionPane.showMessageDialog(billInterfaceService.getFrame(), "Item with code " + itemCode + " not found in the bill.");
        }
    }

    void finalizeBill() {
        stateContext.finalizeBill(this);
        try {
            int billSerialNumber = Integer.parseInt(billInterfaceService.getTfBillSerialNumber().getText());
            double discount = Double.parseDouble(billInterfaceService.getTfDiscount().getText());
            double cashTendered = Double.parseDouble(billInterfaceService.getTfCashTendered().getText());
            double netTotal = subTotal - discount;
            double changeAmount = cashTendered - netTotal;
            String selectedPaymentStrategy = (String) billInterfaceService.getPaymentStrategyComboBox().getSelectedItem();
            PaymentStrategy strategy = strategyMap.get(selectedPaymentStrategy);
            PaymentContext paymentContext = new PaymentContext(strategy);
            String customerName = billInterfaceService.getCustomerName().getText();

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
            builder.addCustomerName(customerName);

            Bill bill = builder.getBill();
            paymentContext.executeStrategy(bill);
            billSaveService.saveBill(bill);

            billInterfaceService.getTfSubtotal().setText(String.format("Subtotal:",
                    subTotal));
            JOptionPane.showMessageDialog(billInterfaceService.getFrame(),"Bill added successfully");
            stateContext.processBill(this);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(billInterfaceService.getFrame(), "Invalid input. Please enter valid numbers for discount and cash tendered.");
        }
    }


    public void checkState() {
        stateContext.checkState();
    }

}
