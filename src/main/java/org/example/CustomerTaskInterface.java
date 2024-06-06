package org.example;

import org.example.Controller.CustomerController;
import org.example.View.CustomerView;

public class CustomerTaskInterface implements PointOfSales {
    @Override
    public void getInterface() {
        CustomerView view = new CustomerView();
        CustomerRepository customerRepository=new CustomerRepository(DatabaseConfiguration.Database.getInstance());
        CustomerController controller = new CustomerController(view, customerRepository);

        view.setVisible(true);
    }
}
