package com.netcracker.kasianova.entities;

import java.io.Serializable;

public abstract class User implements Serializable {

    private String firstName;
    private String lastName;


    User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
