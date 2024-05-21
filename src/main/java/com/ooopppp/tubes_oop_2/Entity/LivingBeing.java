package com.ooopppp.tubes_oop_2.Entity;

import java.util.Map;

public class LivingBeing extends Card{
    protected Map<Item, Integer> activeItem;
    protected Product harvestResult;
    public LivingBeing(String name, Product harvestResult, String image){
        super(name, image);
        this.harvestResult = harvestResult;
    }

    public Product harvest(){
        return harvestResult;
    }

//    public String getActiveItem() {
//        StringBuilder sb = new StringBuilder();
//        for (Map.Entry<Item, Integer> entry : activeItem.entrySet()) {
//            sb.append(entry.getKey().getName()).append(" : ").append(entry.getValue()).append("\n");
//        }
//        return sb.toString();
//    }
}
