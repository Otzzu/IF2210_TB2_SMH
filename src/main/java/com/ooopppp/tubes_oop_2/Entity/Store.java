package com.ooopppp.tubes_oop_2.Entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Store {
    private Map<String, List<Product>> itemSells;

    public Store() {
        itemSells = new HashMap<>();
    }

    public void  addItems(Product item){

    }

    public Product takeItem(String name){
        return null;
    }
}
