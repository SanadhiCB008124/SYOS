package org.example;

public class ProcessState implements POSState{
    @Override
    public void checkState(Context context) {
        System.out.println("POS is in Proccessing State");
        context.setState(this);
    }
}
