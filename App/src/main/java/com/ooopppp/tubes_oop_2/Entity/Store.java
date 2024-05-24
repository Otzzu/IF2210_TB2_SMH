package com.ooopppp.tubes_oop_2.Entity;

import com.ooopppp.tubes_oop_2.Controller.StoreController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
public class Store {
    private Map<String, List<Product>> itemSells;

    public Store() {

        itemSells = new HashMap<>();
    }

    public void addItems(Product item) {
        String productName = item.getName();
        if (!itemSells.containsKey(productName)) {
            itemSells.put(productName, new ArrayList<>());
        }
        // Add the item to the list for that product name
        itemSells.get(productName).add(item);
    }

    public Product takeItem(String name) {
        if (itemSells.containsKey(name) && !itemSells.get(name).isEmpty()) {
            return itemSells.get(name).remove(0);
        }
        return null;
    }

    public Map<String, List<Product>> getItemSells() {
        return itemSells;
    }
}
