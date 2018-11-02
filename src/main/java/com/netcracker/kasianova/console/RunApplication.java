package com.netcracker.kasianova.console;

import com.netcracker.kasianova.dao.filedao.RestaurantBinaryFileDAO;
import com.netcracker.kasianova.entities.Check;
import com.netcracker.kasianova.entities.Order;
import com.netcracker.kasianova.entities.Restaurant;
import com.netcracker.kasianova.entities.Staff;
import com.netcracker.kasianova.services.OrderService;
import com.netcracker.kasianova.services.RestaurantServices;
import com.netcracker.kasianova.utils.Constants;

import java.io.File;
import java.util.Scanner;

public enum RunApplication {
    INSTANCE;

    public void runApplication()
    {
        Constants constants = Constants.INSTANCE;
        OrderService orderService = OrderService.INSTANCE;
        ApplicationFunctions applicationFunctions = ApplicationFunctions.INSTANCE;
        RestaurantBinaryFileDAO restaurantBinaryFileDAO = RestaurantBinaryFileDAO.INSTANCE;
        RestaurantServices restaurantServices = RestaurantServices.INSTANCE;

        Scanner scanner = new Scanner(System.in);
        Restaurant restaurant ;

        boolean FileExists  = restaurantBinaryFileDAO.fileExists("src" + File.separator+"main"+
                File.separator +"java"+File.separator +constants.getFilePath()+constants.getFileName());

        if (!FileExists) {
            restaurant = new Restaurant(3, 20);

            restaurantServices.addStaff(restaurant,"Valera", "AtTime");

            restaurantServices.addDish(restaurant,20, "pizza");
            restaurantServices.addDish(restaurant,10, "pie");
            restaurantServices.addDish(restaurant,2, "pepsi");
            restaurantServices.addDish(restaurant,2, "cola");


            restaurantServices.addTable(restaurant,5);
            restaurantServices.addTable(restaurant,5);

        } else {
            restaurant = restaurantBinaryFileDAO.getObject();
        }

        Staff staff = restaurantServices.getStaff(restaurant,"Valera", "AtTime");

        while (true) {
            if (staff.getListOrders().isEmpty()) {
                System.out.println("Do you want to get new order? \n1-yes \n2-no");
                String answer = scanner.nextLine();

                if ("1".equals(answer)) {
                    Order order = applicationFunctions.acceptOrder(restaurant,orderService, staff);

                    restaurantBinaryFileDAO.saveRestaurant(restaurant);

                    System.out.println("Choose check you want to work ");
                    Check check = applicationFunctions.getCheck(order);

                    applicationFunctions.checkActions(restaurant,check, orderService);
                    restaurantBinaryFileDAO.saveRestaurant(restaurant);



                } else if ("2".equals(answer)) {
                    System.out.println("Lazy boy !");
                    break;
                } else {
                    System.out.println("Incorrect command try again");
                }

            }
            else if (!staff.getListOrders().isEmpty())
            {
                System.out.println("Do you want to get new order or existing ? /n1 - existing /n2 - new ");

                String answer = scanner.nextLine();
                if ("2".equals(answer))
                {
                    Order order = applicationFunctions.acceptOrder(restaurant,orderService,staff);
                    if (order == null) {
                        continue;
                    }
                    restaurantBinaryFileDAO.saveRestaurant(restaurant);

                    System.out.println("Choose check you want to work ");
                    Check check = applicationFunctions.getCheck(order);

                    applicationFunctions.checkActions(restaurant,check, orderService);
                    restaurantBinaryFileDAO.saveRestaurant(restaurant);

                } else if ("1".equals(answer)) {
                    applicationFunctions.workWithExistingOrder(restaurant, orderService);
                    restaurantBinaryFileDAO.saveRestaurant(restaurant);

                } else {
                    System.out.println("Incorrect command try again");
                }
            }
        }
    }
}
