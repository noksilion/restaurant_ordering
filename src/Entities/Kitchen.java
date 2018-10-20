package Entities;

import java.util.ArrayList;
import java.util.Queue;

public class Kitchen {
    private ArrayList<Dish> dishQueue = new ArrayList<Dish>();

    public void addDishToQueue (ArrayList<Dish> listDishes){
        for (Dish dish:listDishes) {
            dishQueue.add(dish);
        }
    }

    public Dish cookDish(){
        return null;
    }


}
