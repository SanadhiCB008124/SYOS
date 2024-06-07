package org.example.Controller;

import org.example.ItemRepository;
import org.example.Product;
import org.example.View.ItemsOnShelfView;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import java.awt.event.ActionEvent;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class ItemsOnShelfControllerTest {

    private ItemsOnShelfView mockView;
    private ItemRepository mockRepository;
    private ItemsOnShelfController controller;

    @Before
    public void setUp() {
        mockView = mock(ItemsOnShelfView.class);
        mockRepository = mock(ItemRepository.class);
        controller = new ItemsOnShelfController(mockView, null, mockRepository);
    }

    @Test
    public void testAddItemToList() {
        // Define test data
        String itemCode = "1001";
        String itemDescription = "Test Item";
        String unitPrice = "10.99";
        String quantityOnShelf = "15";
        String productID = "1";

        // Mock user input in the view
        when(mockView.getItemCode()).thenReturn(itemCode);
        when(mockView.getItemDescription()).thenReturn(itemDescription);
        when(mockView.getUnitPrice()).thenReturn(unitPrice);
        when(mockView.getQuantityOnShelf()).thenReturn(quantityOnShelf);
        when(mockView.getProductID()).thenReturn(productID);

        // Simulate button click event
        controller.new AddItemListener().actionPerformed(mock(ActionEvent.class));

        // Verify that the addItemsOnShelf method in the repository was called with the correct parameters
        ArgumentCaptor<Integer> itemCodeCaptor = ArgumentCaptor.forClass(Integer.class);
        ArgumentCaptor<String> itemDescriptionCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Double> unitPriceCaptor = ArgumentCaptor.forClass(Double.class);
        ArgumentCaptor<Integer> quantityOnShelfCaptor = ArgumentCaptor.forClass(Integer.class);
        ArgumentCaptor<Product> productCaptor = ArgumentCaptor.forClass(Product.class);

        verify(mockRepository).addItemsOnShelf(itemCodeCaptor.capture(), itemDescriptionCaptor.capture(),
                unitPriceCaptor.capture(), quantityOnShelfCaptor.capture(), productCaptor.capture());

        // Verify that the method was called with the expected parameters
        assertEquals(Integer.parseInt(itemCode), itemCodeCaptor.getValue().intValue());
        assertEquals(itemDescription, itemDescriptionCaptor.getValue());
        assertEquals(Double.parseDouble(unitPrice), unitPriceCaptor.getValue(), 0.01);
        assertEquals(Integer.parseInt(quantityOnShelf), quantityOnShelfCaptor.getValue().intValue());
        assertEquals(Integer.parseInt(productID), productCaptor.getValue().getProductID());
    }
}
