package com.netcracker.kasianova.entities;


import java.io.Serializable;
import java.util.LinkedList;


public class Kitchen implements Serializable {
    private LinkedList<Dish> dishQueue = new LinkedList<Dish>();
    private LinkedList<Dish> allCookedDish = new LinkedList<Dish>();

    public LinkedList<Dish> getDishQueue() {
        return dishQueue;
    }

    public LinkedList<Dish> getAllCookedDish() {
        return allCookedDish;
    }


}
