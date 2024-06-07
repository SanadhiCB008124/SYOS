package org.example;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

public class StateContextTest {

    private StateContext stateContext;
    private BillDirector billDirector;

    @Before
    public void setUp() {
        stateContext = new StateContext();
        billDirector = mock(BillDirector.class); // Mocking BillDirector for the tests
    }

    @Test
    public void testInitialState() {
        assertTrue(stateContext.getState() instanceof IdleState);
    }

    @Test
    public void testIdleToProcessTransition() {
        stateContext.processBill(billDirector);
        assertTrue(stateContext.getState() instanceof ProcessState);
    }

    @Test
    public void testProcessToFinalizeTransition() {
        stateContext.processBill(billDirector);
        assertTrue(stateContext.getState() instanceof ProcessState);

        stateContext.finalizeBill(billDirector);
        assertTrue(stateContext.getState() instanceof FinalizedState);
    }

    @Test
    public void testFinalizeToProcessTransition() {
        stateContext.processBill(billDirector);
        stateContext.finalizeBill(billDirector);
        assertTrue(stateContext.getState() instanceof FinalizedState);

        stateContext.processBill(billDirector);
        assertTrue(stateContext.getState() instanceof ProcessState);
    }

    @Test
    public void testProcessToIdleTransition() {
        stateContext.processBill(billDirector);
        assertTrue(stateContext.getState() instanceof ProcessState);

        stateContext.idleBill(billDirector);
        assertTrue(stateContext.getState() instanceof IdleState);
    }

    @Test
    public void testFinalizeToIdleTransition() {
        stateContext.processBill(billDirector);
        stateContext.finalizeBill(billDirector);
        assertTrue(stateContext.getState() instanceof FinalizedState);

        stateContext.idleBill(billDirector);
        assertTrue(stateContext.getState() instanceof IdleState);
    }
}
