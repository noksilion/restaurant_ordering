package com.netcracker.kasianova.entities;

import java.io.Serializable;

public class Dish implements Serializable {

    private int prise;
    private String name;
    private Check check;


    public Dish(int prise,String name){
        this.name = name;
        this.prise = prise;
    }

    public int getPrise() {
        return prise;
    }


    public String getName() {
        return name;
    }

    public Check getCheck() {
        return check;
    }

    public Staff getWaiter(){
        return check.getOrder().getStaff();
    }

    public void setCheck(Check check) {
        this.check = check;
    }
}
