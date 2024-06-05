package org.example;

public class FinalizedState implements POSState{
    @Override
    public void checkState(Context context) {
        System.out.println("POS is in Finalized State");
        context.setState(this);
    }
}
