package org.example;

import junit.framework.TestCase;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class ShelfHandlerTest extends TestCase {

    public void testHandleMovingItemsToTheShelf() {
        // Create a list of Stock items
        List<Stock> stockItems = new ArrayList<>();
        // Add some sample Stock items
        Stock stockItem1 = new Stock(1, 123, "Item A", 1001, 10, Date.valueOf("2024-06-10"), Date.valueOf("2024-06-01"), Date.valueOf("2024-05-30"));
        Stock stockItem2 = new Stock(2, 124, "Item B", 1002, 15, Date.valueOf("2024-06-15"), Date.valueOf("2024-06-01"), Date.valueOf("2024-05-30"));
        stockItems.add(stockItem1);
        stockItems.add(stockItem2);

        // Redirect System.out to capture printed output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Create an instance of ExpiryDateHandler and ShelfHandler
        ExpiryDateHandler expiryDateHandler = new ExpiryDateHandler();
        ShelfHandler shelfHandler = new ShelfHandler();

        // Set ShelfHandler as the next handler for ExpiryDateHandler
        expiryDateHandler.setNextHandler(shelfHandler);

        // Call the method under test
        expiryDateHandler.handleMovingItemsToTheShelf(stockItems);

        // Restore the original System.out
        System.setOut(System.out);

        // Verify the printed output
        String expectedOutput = "";
        assertEquals(expectedOutput, outputStream.toString());
    }
}
