package com.netcracker.kasianova.entities;

import java.io.Serializable;
import java.util.ArrayList;

public class Staff extends User implements Serializable {

    private ArrayList<Order> listOrders = new ArrayList<Order>();
    private Restaurant restaurant;
    Position position;

    public Staff(String firstName, String lastName, Restaurant restaurant) {
        super(firstName, lastName);
        this.restaurant = restaurant;

    }

    public ArrayList<Order> getListOrders() {
        return listOrders;
    }

    public void removeOrder(Order order) {
        listOrders.remove(order);
    }

    public Restaurant getRestaurant() { return this.restaurant;}

}
