package org.example;

import org.example.Controller.CustomerController;
import org.example.View.CustomerView;

public class MVCCustomer {
    public static  void main(String [] args){
        CustomerView view = new CustomerView();
        CustomerModel model = new CustomerModel();
        CustomerController controller = new CustomerController(view, model);

        view.setVisible(true);
    }
}