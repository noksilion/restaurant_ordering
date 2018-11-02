package com.netcracker.kasianova.entities;

import java.io.Serializable;

public class Table implements Serializable {
    private int number;
    private int capasity;
    private boolean statement;
    private Order order;// true - free; false - busy;

    public Table(int capasity, int number) {
        this.statement = true;
        this.capasity = capasity;
        this.number = number;
    }


    public boolean getStatement() {
        return statement;
    }

    public int getCapasity() {
        return capasity;
    }

    public void setStatement(boolean statement) {
        this.statement = statement;
    }

    void addOrder(Order order) {
        this.order = order;
        setStatement(false);
    }

    public void makeTableFree() {
        addOrder(null);
        setStatement(false);
    }

    public int getNumber() {
        return this.number;
    }


    public Order getOrder() {
        return order;
    }
}
