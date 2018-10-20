package Entities;

import java.util.ArrayList;

public class Check {
    private ArrayList<Dish> listDishes= new ArrayList<Dish>();
    private int amount ;

    public Check() {
        this.amount = 0;
    }
    public void addDish(Dish dish){
        listDishes.add(dish);
    }

    public ArrayList<Dish> getListDishes(){
        return listDishes;
    }
}
