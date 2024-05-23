package com.ooopppp.tubes_oop_2.Entity;

import java.util.HashMap;
import java.util.Map;

public class LivingBeing extends Card {
    protected Map<String, Integer> activeItem = new HashMap<>();
    protected Product harvestResult;

    public LivingBeing(String name, Product harvestResult, String image) {
        super(name, image);
        this.harvestResult = harvestResult;
        this.activeItem = new HashMap<>();
    }

    public boolean haveProtect() {
        return activeItem.containsKey("Protect");
    }

    public boolean haveTrap() {
        return activeItem.containsKey("Trap");
    }

    public void useItem(Item item) {

        if (activeItem.containsKey(item.getName())) {
            activeItem.put(item.getName(), activeItem.get(item.getName()) + 1);
        } else {
            activeItem.put(item.getName(), 1);
        }
    }

    public Product harvest() {
        return harvestResult;
    }

    public void print() {
        System.out.println("--------");
        System.out.println("Id: " + id);
        System.out.println("Name: " + name);
        System.out.println("Active Items:");
        for (Map.Entry<String, Integer> entry : activeItem.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        System.out.println("--------");

    }

    public String getActiveItem() {
        return activeItem.toString();
    }

    public Integer getCountActivateItem(String itemName) {
        Integer count = activeItem.get(itemName);
        return (count != null) ? count : 0;  // Return 0 if the count is null
    }
}

//}
