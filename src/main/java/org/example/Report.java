package org.example;

public abstract class Report {
    protected abstract void getDate();
    protected abstract  void createReport();
    protected abstract  void printReport();


    //building the template method

    public final void reportGenerator(){
        getDate();

        createReport();

        printReport();
    }
}
