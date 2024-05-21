package com.ooopppp.tubes_oop_2.Entity;

public abstract class Animal extends LivingBeing{
    protected int weight;
    protected int weightToHarvest;
    public Animal(String name, Product harvestResult, int weightToHarvest, String image) {
        super(name, harvestResult, image);
        this.weightToHarvest = weightToHarvest;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public abstract void eat(Product product);

    public String getImagePath() {
        return image;
    }
}
