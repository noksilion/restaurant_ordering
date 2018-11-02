package com.netcracker.kasianova.entities;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Order implements Serializable {
    private Staff staff;
    private Table table;
    private List<Check> listCheck = new ArrayList<Check>();



    public Order(Staff staff, Table table, ArrayList<String> listCheckName) {
        this.table = table;
        table.addOrder(this);
        this.staff = staff;

        for (String checkName :listCheckName)
        {
            listCheck.add(new Check(this,checkName));
        }
    }

    public List<Check> getAllCheck() {
        return listCheck;
    }
    public Staff getStaff() {
        return staff;
    }
    public Table getTable() {
        return table;
    }
    public void removeCheck (String checkName)
    {
        Check check =getCurrentCheck(checkName);
        listCheck.remove(check);
    }


    public Check getCurrentCheck(String checkName) {
        for (Check check : listCheck) {
            if (check.getName().equals(checkName)) {
                return check;
            }
        }
        return null;
    }





}
