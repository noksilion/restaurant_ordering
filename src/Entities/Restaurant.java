package Entities;

import java.util.ArrayList;

public class Restaurant {
    private int staffCapasity;
    private int generalTableCapasity;

    private ArrayList<Staff> staffList = new ArrayList <Staff>() ;
    private ArrayList<Dish> dishList = new  ArrayList<Dish>();
    private ArrayList<Table> tableList = new  ArrayList<Table> ();

    public Restaurant(int staffCapasity,int generalTableCapasity){
        this.staffCapasity = staffCapasity;
        this.generalTableCapasity  =generalTableCapasity;
    }

    public void addStaff (Staff staff){
        staffList.add(staff);
        // check if staff alrady have in restaurant
    }

    public void addDish(Dish dish){
        dishList.add(dish);
        // check if dish alrady have in restaurant
    }
    public void addTable (Table table){
        tableList.add(table);
        // check if dish alrady have in restaurant
    }
}
