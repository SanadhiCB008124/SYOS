package org.example.Reports;

import org.example.Bill;
import org.example.BillItem;
import org.example.BillIterator;
import org.example.BillRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class BillReportTest {

    private BillReport billReport;
    private BillRepository billRepositoryMock;
    private BillIterator billIteratorMock;

    @Before
    public void setUp() {
        billReport = new BillReport();
        billRepositoryMock = mock(BillRepository.class);
        billIteratorMock = mock(BillIterator.class);
    }

    @Test
    public void testGetData() {
        List<Bill> bills = new ArrayList<>();
        System.out.println("Getting data for Bill Report");
        BillRepository billRepository=new BillRepository();
        BillIterator billIterator=new BillIterator(bills,billRepository);
        billIterator.loadBills();
    }

    @Test
    public void testCreateReport() {
        List<BillItem> billItems = new ArrayList<>();
        billItems.add(new BillItem(1, 2, 100.0, 200.0));
        List<Bill> bills = new ArrayList<>();
        bills.add(new Bill(1, 200.0, billItems, 10.0, 210.0, 10.0, 190.0, new java.util.Date(), 2, "Cash", "John Doe"));

        billReport.createReport();

        // The output will be printed to the console. You can redirect System.out to capture the output and verify it.
    }
}
