package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

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


        Scanner scanner=new Scanner(System.in);
        System.out.println("Print report:" +
                "1. Stock Report" +
                "2. Re-order Report" +
                "3. Bill Report" +
                "4. Sales Report" +
                "5. End of Day Report");
        int choice=scanner.nextInt();
        if(choice==1){
            ReportFacade reportFacade = new ReportFacade();
            reportFacade.generateStockReport();
        } else if (choice==2) {
            ReportFacade reportFacade = new ReportFacade();
            reportFacade.generateReOrderReport();
        } else if (choice==3) {
            ReportFacade reportFacade = new ReportFacade();
            reportFacade.generateBillReport();
        } else if (choice==4) {
            ReportFacade reportFacade = new ReportFacade();
            reportFacade.generateSalesReport();
        } else if (choice==5) {
            ReportFacade reportFacade = new ReportFacade();
            reportFacade.generateEndOfDayReport();
        } else {
            System.out.println("Invalid choice");
        }


        Scanner scanner1=new Scanner(System.in);
        System.out.println("MENU " +
                "1. Add Bill " +
                "2. Customer Management " +
                "3. Product Management " +
                "4. Stock Management " +
                "5. Shelf Management ");

        int choice1=scanner1.nextInt();
        PointOfSalesMenu pointOfSalesMenu = new PointOfSalesMenu();
        PointOfSales pointOfSales = pointOfSalesMenu.getInterface(choice1);
        pointOfSales.getInterface();




    }
}