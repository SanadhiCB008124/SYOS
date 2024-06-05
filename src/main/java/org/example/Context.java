package org.example;

public class Context {
    private POSState state;

    public Context() {
        state=null;
    }

    public POSState getState() {
        return state;
    }

    public void setState(POSState state) {
        this.state = state;
    }
}
