package Entities;

public class Dish {

    private int prise;
    private String name;

    public Dish(int prise,String name){
        this.name = name;
        this.prise = prise;
    }

    public int getPrise() {
        return prise;
    }

    public void setPrise(int prise) {
        this.prise = prise;
    }

    public String getName() {
        return name;
    }

}
