package com.netcracker.kasianova.entities;

import java.io.Serializable;

public class Waiter extends Staff implements Serializable {

    public Waiter(String firstName, String lastName, Restaurant restaurant) {
        super(firstName, lastName, restaurant);
        position = Position.WAITER;
    }

}
