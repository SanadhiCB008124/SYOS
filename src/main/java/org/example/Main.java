package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {

        try {
            Class.forName("org.postgresql.Driver");
            Connection conn = (Connection) DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "Sana@2002");
            System.out.println("connected");

        } catch (ClassNotFoundException e) {
            System.err.println("PostgreSQL JDBC Driver not found: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Error establishing database connection: " + e.getMessage());
        }


       /* Scanner scanner = new Scanner(System.in);
        System.out.println("Enter payment method: 1.Cash");
        int choice = scanner.nextInt();*/


        Stock Stock =new Stock();
        new StockObserver(Stock);
        Stock.checkLowStock();


        System.out.println("first state change");
        Stock.setQuantityInStock(10);


        Item item =new Item();
        new ShelfObserver(item);
        item.setQuantityOnShelf(1);
        item.checkLowStock(1);



        ReportFacade reportFacade = new ReportFacade();
        reportFacade.generateStockReport();

        System.out.println("Generating Sales Report...");
        reportFacade.generateSalesReport();

        System.out.println("Generating End of Day Report...");
        reportFacade.generateEndOfDayReport();

        System.out.println("Generating ReOrder Report...");
        reportFacade.generateReOrderReport();

        System.out.println("Generating Stock Report...");
        reportFacade.generateStockReport();

        Bill bill=new Bill();
        List<Bill> bills = bill.allBills();
        System.out.println("Generating Bill Report...");
        reportFacade.generateBillReport(bills);

    }
}