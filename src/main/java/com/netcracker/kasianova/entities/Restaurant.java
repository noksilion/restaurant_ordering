package com.netcracker.kasianova.entities;


import java.io.Serializable;
import java.util.ArrayList;


public class  Restaurant implements Serializable {
    private int staffCapasity;
    private int maxTableCapasity;



    private ArrayList<Staff> staffList = new ArrayList<Staff>();
    private ArrayList<Dish> dishList = new ArrayList<Dish>();
    private ArrayList<Table> tableList = new ArrayList<Table>();
    private Kitchen kitchen = new Kitchen();



    public Restaurant(int staffCapasity, int maxTableCapasity) {
        this.staffCapasity = staffCapasity;
        this.maxTableCapasity = maxTableCapasity;
    }

    public ArrayList<Table> getTableList() {
        return tableList;
    }

    public ArrayList<Staff> getStaffList() {
        return staffList;
    }

    public ArrayList<Dish> getDishList() {
        return dishList;
    }

    public int getMaxTableCapasity() {
        return maxTableCapasity;
    }

    public int getStaffCapasity() {
        return staffCapasity;
    }

    public Kitchen getKitchen() {
        return kitchen;
    }
}
