package com.netcracker.kasianova.console;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class RestaurauntConsole implements Serializable {
    public void toConsoleNoWorkplaces ()
    {System.out.println("Can't add Staff because no able workplaces ");}

    public List<String> addNewName()
    {
        List<String> nameList  = new ArrayList<String>();

        System.out.println("Can't add Staff because this name is already used");
        Scanner scanner = new Scanner(System.in);

        System.out.println("Add another first name - ");
        nameList.add(scanner.nextLine());

        System.out.println("Add another last name - ");
        nameList.add(scanner.nextLine());

        return nameList;
    }

    public void toConsoleDishExists ()
    { System.out.println("This Dish is already exists");}

    public void toConsoleNoSeats ()
    {System.out.println("Cant add table because maximum number of seats reached");}

    public List<String> toConsoleGetStaffName(String firstName, String lastName)
    {
        List<String> listName = new ArrayList<String>();

        System.out.println("There are no Staff with name " + firstName + " " + lastName);

        Scanner scanner = new Scanner(System.in);

        System.out.print("Write first name for Staff - ");
        listName.add(scanner.nextLine());

        System.out.print("Write last name for Staff - ");
        listName.add(scanner.nextLine());

        return listName;
    }

}
