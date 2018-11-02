package com.netcracker.kasianova.services;

import com.netcracker.kasianova.entities.Dish;
import com.netcracker.kasianova.entities.Check;

import java.util.ArrayList;

public enum  CheckServices {
    INSTANCE;

    public void addNotCookedDish(Check check,Dish dish, int numberDish) {
        for (int i = 0; i < numberDish; i++) {
            dish.setCheck(check);
            check.getListNotCookedDishes().add(dish);
        }
    }
    public static void addCookedDish(Check check, Dish dish) {
        check.getListCookedDishes().add(dish);
        check.setAmount(dish.getPrise());
        check.getListNotCookedDishes().remove(dish);
    }


}
