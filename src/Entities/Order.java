package Entities;

import java.util.ArrayList;

public class Order {
    private Waiter waiter;
    private Table table;
    private ArrayList<Check> checkList = new ArrayList<Check>();

    public Order(Waiter waiter,Table table)
    {
        this.table = table;
        this.waiter = waiter;
    }

    public boolean addCheck(Check check){
        checkList.add(check);
        return true;
    }

    public ArrayList<Dish> getAllOrderDishes(){
        ArrayList<Dish> listDishes = new ArrayList<Dish>();
        for (Check check: checkList) {
            listDishes.addAll(check.getListDishes()) ;
        }
        return listDishes;
    }


}
