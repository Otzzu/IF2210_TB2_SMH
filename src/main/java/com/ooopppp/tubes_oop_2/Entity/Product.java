package com.ooopppp.tubes_oop_2.Entity;

public class Product extends Card{
    private int price;
    private int addedWeight;

    public Product(String name, int price, int addedWeight, String image) {
        super(name, image);
        this.price = price;
        this.addedWeight = addedWeight;
    }
}
