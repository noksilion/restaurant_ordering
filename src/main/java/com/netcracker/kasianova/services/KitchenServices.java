package com.netcracker.kasianova.services;

import com.netcracker.kasianova.entities.Dish;
import com.netcracker.kasianova.entities.Kitchen;
import com.netcracker.kasianova.entities.Staff;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public enum KitchenServices {
    INSTANCE;

    public List<Dish> cookAllDishes(Kitchen kitchen, Staff staff)
    {
        LinkedList<Dish> dishQueue =kitchen.getDishQueue();
        LinkedList<Dish> allCookedDish =kitchen.getAllCookedDish();

        allCookedDish.addAll(dishQueue);
        dishQueue.clear();

        return getCookedDishesForWaiter(kitchen,staff);
    }


    public static void addDishToQueue(Kitchen kitchen, ArrayList<Dish> listDishes) {

        LinkedList<Dish> dishQueue =kitchen.getAllCookedDish();
        dishQueue.addAll(listDishes);
    }

    public List<Dish> getCookedDishesForWaiter(Kitchen kitchen,Staff staff)
    {
        LinkedList<Dish> allCookedDish =kitchen.getAllCookedDish();
        List<Dish> cookedDishesForWaiter = new ArrayList<Dish>();

        for (Dish dish : allCookedDish) {
            if (dish.getWaiter() == staff) {
                cookedDishesForWaiter.add(dish);
            }
        }
        return cookedDishesForWaiter;
    }
}
