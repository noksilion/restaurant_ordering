package com.netcracker.kasianova.console;

import com.netcracker.kasianova.entities.Dish;
import com.netcracker.kasianova.entities.Staff;

import java.io.Serializable;
import java.util.Scanner;

public class OrderServicesConsole implements Serializable {

    public void noPlacesToConsole ()
    { System.out.println("Sorry we have no places for you");}

    public void more0GuestsToConsole()
    {System.out.println("Need to add no more then " + 0 + " guests");}

    public int inputNewNumberCheck()
    {
        String newNumberCheck = null
                ;
        while (newNumberCheck == null) {
            System.out.print("Enter number check - ");
            Scanner scanner = new Scanner(System.in);

            newNumberCheck = scanner.nextLine();
            try {
                 Integer.parseInt(newNumberCheck);
            } catch (NumberFormatException e) {
                System.out.println("Incorrect input number check ");
                newNumberCheck = null;
            }
        }
        return Integer.parseInt(newNumberCheck);
    }

    public void noMoreTableToConsole ()
    {System.out.println("There are no table with this capacity come next time :)");}

    public void writeNameForCheckToConsole()
    {System.out.print("Write name for check - ");}

    public void nameExistsToConsole()
    {System.out.println("this name exists "); }

    public void orderAcceptedToConsole()
    {System.out.println("Order accepted");}

    public void noNotCookedDishesInOrderToConsole()
    {System.out.println("There are no not cooked dishes in order :)");}

    public void dishesSandedToKithenToConsole ()
    {System.out.println("Dishes sanded to kitchen");}

    public void noCookedDishesInKitchenToConsole()
    {System.out.println("There are no cooked Dishes in Kitchen");}

    public void bringDishByStaff (Staff staff, Dish dish)
    {
        System.out.println(dish.getName() + " brought to table by  - " + staff.getFirstName() + " "
            + staff.getLastName());
    }

    public void incorrectCheckNameToConsole()
    {System.out.println("Incorrect check name, enter new check name - ");}

    public void dishAddedToConsole (Dish dish,int numberDish,String newCheckName)
    {
        System.out.println("Dish - " + dish.getName() + " with number "+numberDish
            +" added to check with name  -" + newCheckName );
    }

}
