package Entities;

import java.util.ArrayList;

public class Staff extends User {
    private ArrayList<Order> listOrders = new ArrayList<>();
    static Position position;

    public Staff(String firstName, String lastName) {
        super(firstName, lastName);
        this.position = Position.WAITER;
    }

    public ArrayList<Dish> getAllDishes(){
        ArrayList<Dish> listDishes = new ArrayList<Dish>();
        for (Order order:listOrders){
            listDishes.addAll(order.getAllOrderDishes());
            }
            return  listDishes;
    }
}
