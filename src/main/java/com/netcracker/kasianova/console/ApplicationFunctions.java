package com.netcracker.kasianova.console;

import com.netcracker.kasianova.entities.*;
import com.netcracker.kasianova.services.OrderService;
import com.netcracker.kasianova.services.RestaurantServices;
import com.netcracker.kasianova.services.StaffService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public enum ApplicationFunctions implements Serializable {
    INSTANCE;
    RestaurantServices restaurantServices = RestaurantServices.INSTANCE;
    StaffService staffService = StaffService.INSTANCE;

    Order acceptOrder(Restaurant restaurant ,OrderService orderService, Staff staff) {

        System.out.println("Enter number guests - ");
        int numberGuests = getIntFromConsole("Incorrect input number guests ");

        System.out.println("Enter number check - ");
        int numberCheck = getIntFromConsole("Incorrect input number check ");

        if (restaurantServices.getFreeSeats(restaurant) <= 0)
        {return null;}

        return orderService.acceptOrder(restaurant,staff, numberGuests, numberCheck);
    }

    private  int getIntFromConsole(String message)
    {
        while(true)
        {
            Scanner scanner = new Scanner(System.in);
            String numberToConvert = scanner.nextLine();

            try {
                Integer.parseInt(numberToConvert);
            } catch (NumberFormatException e) {
                System.out.println(message);
                continue;
            }
            return Integer.parseInt(numberToConvert);
        }
    }

    Check getCheck(Order order) {
        Scanner scanner = new Scanner(System.in);
        Check check = null;
        while (check == null) {
            for (Check check1 : order.getAllCheck()) {
                System.out.println("Check name - " + check1.getName());
            }

            System.out.println("Choose check name you want to work - ");
            String checkName = scanner.nextLine();

            check = order.getCurrentCheck(checkName);
            if (check == null) {
                System.out.println("Incorrect check name");
            }
        }
        return check;
    }

    private Dish getDish(Restaurant restaurant) {
        Scanner scanner = new Scanner(System.in);
        Dish dish = null;

        while (dish == null) {
            ArrayList <Dish> dishArrayList = restaurant.getDishList();

            for (Dish dish1 : dishArrayList) {
                System.out.println(dish1.getName() + " - price -  " + dish1.getPrise());
            }
            System.out.println("Choose dish name you want to work - ");
            String dishName = scanner.nextLine();

            for(Dish dish1 : dishArrayList)
            {
                if (dishName.equals(dish1.getName()))
                {
                    dish = dish1;
                }
            }
            if (dish == null) {
                System.out.println("Incorrect dish name");
            }
        }
        return dish;
    }


    private int getNumberDishes() {
            System.out.println("Enter dish number - ");

        return getIntFromConsole("Incorrect input number dish ");
    }

    void checkActions(Restaurant restaurant ,Check check, OrderService orderService) {

        Scanner scanner = new Scanner(System.in);

        String answerCheck = "";
        while ("".equals(answerCheck))
        {
            System.out.println("What do you want to do with this check /n 1 - add dish /n 2 - print check  " +
                    "/n3 - bring not cooked dishes to kitchen");

            String answerCheck1 = scanner.nextLine();

            if ("1".equals(answerCheck1)) {

                System.out.println("Choose dish you want to add to check");
                Dish dish = getDish(check.getOrder().getStaff().getRestaurant());

                int numberDishes = getNumberDishes();
                Staff staff =check.getOrder().getStaff();

                orderService.addMoreDishesToCheck(staff, check.getName(), dish, numberDishes);

                while (true) {
                    System.out.println("Do you want to work more with this check /n 1 - yes /n 2 - no");
                    String answerCheck2 = scanner.nextLine();

                    if ("1".equals(answerCheck2)) {
                        answerCheck = "";
                        break;
                    } else if ("2".equals(answerCheck2)) {
                        answerCheck = answerCheck1;
                        break;
                    }
                }

            } else if ("2".equals(answerCheck1)) {
                printCheck(check.getOrder(), check.getName());
                if(check.getOrder().getAllCheck().isEmpty())
                {
                    Order order = check.getOrder();
                    Staff staff =order.getStaff();

                    staff.removeOrder(order);
                }
                answerCheck = answerCheck1;
            }
            else if ("3".equals(answerCheck1)) {
                Order order = check.getOrder();
                Staff staff = check.getOrder().getStaff();
                Kitchen kitchen = restaurant.getKitchen();

                boolean sendDishesToKithenResult = orderService.sendDishesToKitchen(order, kitchen);
                if (!sendDishesToKithenResult) {
                    break;
                }

                List<Dish> dishList = orderService.cookDishesForWaiter(staff, kitchen);

                boolean bringCookedDishesToTablesResult = orderService.bringCookedDishesToTables(staff, dishList);

                if (!bringCookedDishesToTablesResult) {
                    break;
                }
                answerCheck = answerCheck1;

                while (true) {
                    System.out.println("Do you want to work more with this check /n 1 - yes /n 2 - no");
                    String answerCheck2 = scanner.nextLine();

                    if ("1".equals(answerCheck2)) {
                        answerCheck = "";
                        break;
                    } else if ("2".equals(answerCheck2)) {
                        break;
                    }
                }

            }
            else {
                System.out.println("Incorrect command ");
            }
        }

    }

    void workWithExistingOrder(Restaurant restaurant, OrderService orderService) {
        Staff staff =restaurantServices.getStaff(restaurant,"Valera", "AtTime");

        ArrayList<Order> orderArrayList = staff.getListOrders();
        Order order = null;

            System.out.println("Choose table number you want to work");
            List<Integer> integerArrayList = new ArrayList<Integer>();

            for (Order order1 : orderArrayList) {
                System.out.println(order1.getTable().getNumber());
                integerArrayList.add(order1.getTable().getNumber());
            }

            int tableNumber  = getIntFromConsole("Incorrect input number table ");


            if (integerArrayList.add(tableNumber)) {
                order = staffService.getOrder(staff,restaurantServices.getTable(restaurant,tableNumber));
            }

        System.out.println("Choose check you want to work ");
        Check check = this.getCheck(order);

        checkActions(restaurant,check, orderService);

    }

    public void printCheck(Order order, String checkName) {
        OrderService orderService = OrderService.INSTANCE;
        ArrayList<String> stringForPrint = orderService.printCheck(order,checkName);
        for (String string : stringForPrint) {
            System.out.println(string);
        }
        order.removeCheck(checkName);
    }

}
