package org.example.Controller;

import org.example.CustomerModel;
import org.example.View.CustomerView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerController {

    private CustomerView theView;
    private CustomerModel theModel;

    public CustomerController(CustomerView theView, CustomerModel theModel) {
        this.theView = theView;
        this.theModel = theModel;

        this.theView.addCustomerListener(new CustomerListener());
    }

    class CustomerListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String customerFirstName;
            String customerLastName;

            try {
                customerFirstName = theView.getCustomerFirstName();
                customerLastName = theView.getCustomerLastName();

                theModel.addCustomer(customerFirstName, customerLastName);
                theView.setCustomerName(theModel.getCustomerName());
                theView.displaySuccessMessage("Customer added successfully!");
            } catch (Exception exception) {
                theView.displayError("Error: " + exception.getMessage());
            }
        }
    }
}

