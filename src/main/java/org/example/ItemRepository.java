package org.example;

import DatabaseConfiguration.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemRepository {
    private static final int SHELF_SIZE =20 ;
    private Item item;


    public void reStockShelf() {
        String SQL_SELECT_ITEMS = "SELECT itemcode FROM item";
        String SQL_UPDATE = "UPDATE item SET qtyonshelf = ? WHERE itemcode = ?";

        try (Connection conn = Database.connect();
             PreparedStatement pstmtSelect = conn.prepareStatement(SQL_SELECT_ITEMS);
             ResultSet rs = pstmtSelect.executeQuery()) {

            try (PreparedStatement pstmtUpdate = conn.prepareStatement(SQL_UPDATE)) {
                while (rs.next()) {
                    int itemCode = rs.getInt("itemcode");
                    pstmtUpdate.setInt(1, SHELF_SIZE);
                    pstmtUpdate.setInt(2, itemCode);
                    pstmtUpdate.executeUpdate();
                }
                System.out.println("All items restocked with a quantity of " + SHELF_SIZE + " successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error restocking all items: " + e.getMessage());
        }
    }


    public void addItemsOnShelf(Integer itemCode, String itemDescription, double unitPrice, Integer quantityOnShelf, Product product) {

        String SQL_INSERT = "INSERT INTO item(itemcode, itemdescription, unitprice, qtyonshelf, productid) VALUES(?, ?, ?, ?, ?)";

        try (Connection conn = Database.connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL_INSERT)) {

            pstmt.setInt(1, itemCode);
            pstmt.setString(2, itemDescription);
            pstmt.setDouble(3, unitPrice);
            pstmt.setInt(4, quantityOnShelf);
            pstmt.setInt(5, product.getProductID());


            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error adding ItemsOnShelf to database: " + e.getMessage());
        }
    }

}
