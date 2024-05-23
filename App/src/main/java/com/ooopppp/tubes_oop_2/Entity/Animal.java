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

    @Override
    public void useItem(Item item) {
        super.useItem(item);
        if (item instanceof ItemAdded itemAdded){
            weight += itemAdded.getAddedWeight();
            if (weight < 0) {
                weight = 0;
            }
        }
    }

    @Override
    public void print() {
        super.print();
        System.out.println("Weight: " + weight);
    }

    public abstract void eat(Product product);

//    public String getImagePath() {
//        return image;
//    }
}
