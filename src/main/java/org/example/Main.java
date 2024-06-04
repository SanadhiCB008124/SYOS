package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        try {
            Class.forName("org.postgresql.Driver");
            Connection conn = (Connection) DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "Sana@2002");
            System.out.println("connected");

        } catch (ClassNotFoundException e) {
            System.err.println("PostgreSQL JDBC Driver not found: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Error establishing database connection: " + e.getMessage());
        }


        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter payment method: 1.Cash");
        int choice = scanner.nextInt();
        PaymentFactory paymentFactory=new PaymentFactory();
        Payment payment=paymentFactory.getPaymentMethod(choice);
        System.out.println("selected payment method:"+ payment.paymentProcess());















    }
}