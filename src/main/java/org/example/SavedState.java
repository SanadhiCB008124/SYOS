package org.example;

public class SavedState implements POSState{
    @Override
    public void checkState(Context context) {
        System.out.println("POS is in Saved State");
        context.setState(this);
    }
}
