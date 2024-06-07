package org.example;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ProductIteratorTest extends TestCase {

    private Connection connection;
    private ProductIterator productIterator;

    @Before
    public void setUp() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "Sana@2002");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            fail("Failed to establish a database connection");
        }
    }

    @After
    public void tearDown() throws Exception {
        // Close the database connection after each test
        if (connection != null) {
            connection.close();
        }
    }

    @Test
    public void testHasNext() {
        productIterator = new ProductIterator();
        assertNotNull(productIterator); // Assuming the constructor doesn't throw any exceptions
        assertTrue(productIterator.hasNext()); // Assuming there are products in the database
    }

    @Test
    public void testNext() {
        productIterator = new ProductIterator();
        assertNotNull(productIterator); // Assuming the constructor doesn't throw any exceptions
        assertNotNull(productIterator.next()); // Assuming there is at least one product in the database
    }
}
