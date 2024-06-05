package org.example;

public class IdleState implements POSState{
    @Override
    public void checkState(Context context) {
        System.out.println("POS is in Idle State");
        context.setState(this);
    }
}
