package org.example.Controller;

import org.example.CashPayment;
import org.example.CreditCardPayment;
import org.example.Customer;
import org.example.CustomerFactory;
import org.example.View.CustomerView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class CustomerController {

    private CustomerView theView;
    private Customer theModel;

    public CustomerController(CustomerView theView, Customer theModel) {
        this.theView = theView;
        this.theModel = theModel;

        this.theView.addCustomerListener(new CustomerListener());

    }

    class CustomerListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String customerFirstName;
            String customerLastName;
            String customerType;

            try {
                customerFirstName = theView.getCustomerFirstName();
                customerLastName = theView.getCustomerLastName();
                customerType=theView.getCustomerType();

                theModel = CustomerFactory.createCustomer(customerType);
                theModel.addCustomer(customerFirstName, customerLastName);
                theView.setCustomerName(theModel.getCustomerName());
                theView.displaySuccessMessage("Customer added successfully!");
            } catch (Exception exception) {
                theView.displayError("Error: " + exception.getMessage());
            }
        }
    }
}

