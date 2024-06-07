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
        String SQL_SELECT_ITEMS = "SELECT itemcode, qtyonshelf FROM item";
        String SQL_UPDATE_ITEM = "UPDATE item SET qtyonshelf = ? WHERE itemcode = ?";
        String SQL_UPDATE_STOCK = "UPDATE stockitem SET quantityinstock = quantityinstock - ? WHERE itemcode = ?";

        try (Connection conn = Database.connect();
             PreparedStatement pstmtSelect = conn.prepareStatement(SQL_SELECT_ITEMS);
             ResultSet rs = pstmtSelect.executeQuery()) {

            try (PreparedStatement pstmtUpdateItem = conn.prepareStatement(SQL_UPDATE_ITEM);
                 PreparedStatement pstmtUpdateStock = conn.prepareStatement(SQL_UPDATE_STOCK)) {

                while (rs.next()) {
                    int itemCode = rs.getInt("itemcode");
                    int qtyOnShelf = rs.getInt("qtyonshelf");
                    int quantityToAdd = Math.max(0, SHELF_SIZE - qtyOnShelf);

                    // Update the quantity on shelf by adding the quantity to add
                    pstmtUpdateItem.setInt(1, qtyOnShelf + quantityToAdd);
                    pstmtUpdateItem.setInt(2, itemCode);
                    pstmtUpdateItem.executeUpdate();

                    // Update stock quantityinstock by reducing the stock quantity by the quantity added to the shelf
                    pstmtUpdateStock.setInt(1, quantityToAdd);
                    pstmtUpdateStock.setInt(2, itemCode);
                    pstmtUpdateStock.executeUpdate();
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
