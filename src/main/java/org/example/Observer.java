package org.example;

import javax.security.auth.Subject;

public abstract class Observer {
    protected batchItem batchItem;
    protected Items items;

    public abstract void update();

    public abstract void lowStockAlert();

}
