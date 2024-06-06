package org.example;

import org.example.Controller.CustomerController;
import org.example.View.CustomerView;

public class MVCCustomer {
    public static  void main(String [] args){
        CustomerView view = new CustomerView();
        CustomerRepository customerRepository=new CustomerRepository(DatabaseConfiguration.Database.getInstance());
        CustomerController controller = new CustomerController(view, customerRepository);

        view.setVisible(true);
    }
}
