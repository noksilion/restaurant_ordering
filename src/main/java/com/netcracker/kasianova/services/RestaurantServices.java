package com.netcracker.kasianova.services;

import com.netcracker.kasianova.console.RestaurauntConsole;
import com.netcracker.kasianova.entities.*;

import java.util.ArrayList;
import java.util.List;

public enum RestaurantServices {
    INSTANCE;

    private RestaurauntConsole restaurauntConsole = new RestaurauntConsole();

    public void addStaff(Restaurant restaurant, String firstName, String lastName) {

        if (restaurant.getStaffList().size() == restaurant.getMaxTableCapasity()) {
            restaurauntConsole.toConsoleNoWorkplaces();
            return;
        }
        String newFirstName = firstName;
        String newLastName = lastName;

        ArrayList<String> firstNameList = new ArrayList<String>();
        ArrayList<String> lastNameList = new ArrayList<String>();

        for (Staff staff : restaurant.getStaffList())
        {
            firstNameList.add(staff.getFirstName());
            lastNameList.add(staff.getLastName());
        }

        if (!firstNameList.contains(firstName) && !lastNameList.contains(lastName))
        {
            Staff staff = new Staff(firstName,lastName,restaurant);
            restaurant.getStaffList().add(staff);
            return;
        }

        List<String> nameList  ;

        do {
            nameList = restaurauntConsole.addNewName();
            firstName = nameList.get(0);
            lastName =nameList.get(1);
        }
        while (firstNameList.contains(firstName) && lastNameList.contains(lastName));

        Staff staff = new Staff(newFirstName, newLastName, restaurant);
        restaurant.getStaffList().add(staff);

    }

    private int getGeneralTableCapasity(Restaurant restaurant) {
        int generalTableCapasity = 0;
        for (Table table : restaurant.getTableList()) {
            generalTableCapasity += table.getCapasity();
        }
        return generalTableCapasity;
    }

    public int getFreeSeats(Restaurant restaurant) {
        int freeSeats = 0;
        for (Table table : restaurant.getTableList()) {
            if (table.getStatement()) {
                freeSeats = freeSeats + table.getCapasity();
            }
        }
        return freeSeats;
    }

    public void addDish(Restaurant restaurant,int prise, String name) {
        for (Dish dish : restaurant.getDishList()) {
            if (dish.getName().equals(name)) {
                restaurauntConsole.toConsoleDishExists();
                return;
            }
        }

        Dish dish = new Dish(prise, name);
        restaurant.getDishList().add(dish);
    }

    public Table getTable(Restaurant restaurant,int numberTable) {
        for (Table table : restaurant.getTableList()) {
            if (table.getNumber() == numberTable) {
                return table;
            }
        }
        return null;
    }


    public void addTable(Restaurant restaurant,int capacity) {
        if (getGeneralTableCapasity(restaurant) + capacity > restaurant.getMaxTableCapasity()) {
            restaurauntConsole.toConsoleNoSeats();
            return;
        }

        int tableNumber = 1;
        for (Table table : restaurant.getTableList()) {
            if (table.getNumber() == tableNumber) {
                tableNumber += 1;
            }
        }

        Table table = new Table(capacity, tableNumber);
        restaurant.getTableList().add(table);

    }

    public Table getFreeTable(Restaurant restaurant,int tableCapasity) {
        Table table1;


        for (Table table : restaurant.getTableList()) {

            if (table.getStatement() && table.getCapasity() >= tableCapasity) {
                table1 = table;
                return table1;
            }
        }

        return null;
    }

    public Dish getDish(Restaurant restaurant,String dishName) {
        for (Dish dish : restaurant.getDishList()) {
            if (dish.getName().equals(dishName)) {
                return dish;
            }
        }
        return null;
    }

    public Staff getStaff(Restaurant restaurant, String firstName, String lastName) {


        ArrayList<String> existingFirstNames = new ArrayList<String>();
        ArrayList<String> existingLastNames = new ArrayList<String>();

        for(Staff staff:restaurant.getStaffList())
        {
            existingFirstNames.add(staff.getFirstName());
            existingLastNames.add(staff.getLastName());
            if (staff.getFirstName().equals(firstName) && staff.getLastName().equals(lastName))
            {
                return staff;
            }
        }

        List<String> listName;

        listName = restaurauntConsole.toConsoleGetStaffName(firstName,lastName);
        firstName = listName.get(0);
        lastName = listName.get(1);
        return new Staff(firstName,lastName,restaurant);
    }


}
