package org.example;

import javax.security.auth.Subject;

public abstract class Observer {
    protected batchItem batchItem;

    public abstract void update();
}
