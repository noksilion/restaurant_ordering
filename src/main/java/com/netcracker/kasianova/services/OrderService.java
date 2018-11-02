package com.netcracker.kasianova.services;


import com.netcracker.kasianova.entities.*;
import com.netcracker.kasianova.console.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public enum OrderService {
    INSTANCE;

    OrderServicesConsole orderServicesConsole =new OrderServicesConsole();
    CheckServices checkServices =CheckServices.INSTANCE;
    KitchenServices kitchenServices =KitchenServices.INSTANCE;
    RestaurantServices restaurantServices = RestaurantServices.INSTANCE;
    StaffService  staffService = StaffService.INSTANCE;

    //TODo
    public Order acceptOrder(Restaurant restaurant,Staff staff, int numberPeople, int numberCheck)
    {

            if (numberPeople > restaurantServices.getFreeSeats(restaurant)) {
               orderServicesConsole.noPlacesToConsole();
                return null;
            }

            if (numberPeople <= 0) {
                orderServicesConsole.more0GuestsToConsole();
                return null;
            }

            if (numberCheck <= 0) {
                orderServicesConsole.more0GuestsToConsole();

                numberCheck = orderServicesConsole.inputNewNumberCheck();
            }

            Table table = restaurantServices.getFreeTable(restaurant,numberPeople);

            if (table == null) {
                orderServicesConsole.noMoreTableToConsole();
                return null;
            }

            ArrayList<String> listCheckName = new ArrayList<String>();
            Scanner scanner = new Scanner(System.in);

            for (int i = 0; i < numberCheck; i++) {
                String newCheckName = "";

                while (newCheckName.equals(""))
                {
                    orderServicesConsole.writeNameForCheckToConsole();

                    newCheckName = scanner.nextLine();
                    for (String checkName: listCheckName) {
                        if (newCheckName.equals(checkName)) {
                            orderServicesConsole.nameExistsToConsole();
                            newCheckName = "";
                            break;
                        }
                    }
                }
                listCheckName.add(newCheckName);
            }

            Order order = staffService.addOrder(staff, table,listCheckName);

            orderServicesConsole.orderAcceptedToConsole();

        return order;
    }


    public boolean sendDishesToKitchen(Order order, Kitchen kitchen) {
        ArrayList<Dish> dishList = getAllNotCookedOrderDishes(order);

        if (dishList.size() == 0) {
            orderServicesConsole.noNotCookedDishesInOrderToConsole();
            return false;
        }
        else {
            KitchenServices.addDishToQueue(kitchen,dishList);
            orderServicesConsole.dishesSandedToKithenToConsole();
            return true;
        }
    }

    public List<Dish> cookDishesForWaiter(Staff staff, Kitchen kitchen) {
        return kitchenServices.cookAllDishes(kitchen,staff);
    }


    public boolean bringCookedDishesToTables(Staff staff, List<Dish> allCookedDishForWaiter) {

        if (allCookedDishForWaiter.size() == 0) {
            orderServicesConsole.noCookedDishesInKitchenToConsole();
            return false;
        }

        for (Dish dish : allCookedDishForWaiter) {
            ArrayList<Order> orderArrayList = staff.getListOrders();
            Order order =dish.getCheck().getOrder();

            if (orderArrayList.contains(order))
            {
                addDishToListCookedCheck(order,dish);
                orderServicesConsole.bringDishByStaff(staff,dish);
            }
        }
        return true;
    }

    public void addMoreDishesToCheck(Staff staff, String checkName, Dish dish, int numberDish) {
        Check check = staffService.getCheck(staff,checkName);

        String newCheckName = checkName;
        while (check == null) {
            orderServicesConsole.incorrectCheckNameToConsole();
            Scanner scanner = new Scanner(System.in);

            newCheckName = scanner.nextLine();
            check = staffService.getCheck(staff,newCheckName);
        }

        checkServices.addNotCookedDish(check,dish, numberDish);
        orderServicesConsole.dishAddedToConsole(dish,numberDish,newCheckName);

    }








    public ArrayList<Dish> getAllNotCookedOrderDishes(Order order) {
        ArrayList<Dish> listDishes = new ArrayList<Dish>();

        for (Check check : order.getAllCheck()) {
            listDishes.addAll(check.getListNotCookedDishes());
        }

        return listDishes;
    }



    public void addDishToListCookedCheck(Order order,Dish dish) {
        for (Check check : order.getAllCheck()) {
            if (dish.getCheck() == check) {
                CheckServices.addCookedDish(check,dish);
            }
        }
    }



    public Check getCurrentCheck(Order order,String checkName) {
        for (Check check : order.getAllCheck()) {
            if (check.getName().equals(checkName)) {
                return check;
            }
        }
        return null;
    }

    public ArrayList<String> printCheck(Order order,String checkName) {
        Check check = order.getCurrentCheck(checkName);

        ArrayList<String> stringsForPrint = new ArrayList<String>();
        Staff staff = order.getStaff();
        stringsForPrint.add("Waiter - " + staff.getFirstName() + " " + staff.getLastName());
        stringsForPrint.add("Table - " + order.getTable().getNumber());


        if (check.getListNotCookedDishes().isEmpty()) {

            for (Dish dish : check.getListCookedDishes()) {
                stringsForPrint.add(dish.getName() + " - " + dish.getPrise());
            }

            stringsForPrint.add("Total amount - " + check.getAmount());
            return stringsForPrint;
        }
        else {
            stringsForPrint.add("Not Cooked Dishes");
            for (Dish dish : check.getListNotCookedDishes()) {
                stringsForPrint.add(dish.getName());
            }

            stringsForPrint.add("Cooked Dishes");
            for (Dish dish : check.getListCookedDishes()) {
                stringsForPrint.add(dish.getName() + " - " + dish.getPrise());
            }

            stringsForPrint.add("Total amount - " + check.getAmount());

            return stringsForPrint;
        }

    }




}
