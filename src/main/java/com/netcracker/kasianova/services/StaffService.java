package com.netcracker.kasianova.services;

import com.netcracker.kasianova.entities.Check;
import com.netcracker.kasianova.entities.Order;
import com.netcracker.kasianova.entities.Staff;
import com.netcracker.kasianova.entities.Table;

import java.util.ArrayList;

public enum StaffService {
    INSTANCE;

    public Order getOrder(Staff staff, Table table) {
        for (Order order : staff.getListOrders()) {
            if (order.getTable() == table) {
                return order;
            }
        }
        return null;
    }


    public Check getCheck(Staff staff, String checkName) {
        for (Order order : staff.getListOrders()) {
            for (Check check : order.getAllCheck()) {
                if (check.getName().equals(checkName)) {

                    return check;
                }
            }
        }
        return null;
    }


    public Order addOrder(Staff staff, Table table, ArrayList<String> listCheckName) {

        Order order = new Order(staff, table,listCheckName);

        staff.getListOrders().add(order);

        return order;
    }
}
