package com.netcracker.kasianova.entities;

import java.io.Serializable;

import java.util.ArrayList;

public class Check implements Serializable {
    private ArrayList<Dish> listNotCookedDishes = new ArrayList<Dish>();
    private ArrayList<Dish> listCookedDishes = new ArrayList<Dish>();
    private int amount = 0;
    private Order order;
    private String name;


    Check(Order order, String name) {
        this.order = order;
        this.name = name;
    }


    public ArrayList<Dish> getListNotCookedDishes() {
        return listNotCookedDishes;
    }

    public ArrayList<Dish> getListCookedDishes() {
        return listCookedDishes;
    }

    public Order getOrder() {
        return order;
    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }
    public void setAmount (int price)
    {
        amount += price;
    }
}
