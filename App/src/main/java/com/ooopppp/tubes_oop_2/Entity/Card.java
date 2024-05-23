package com.ooopppp.tubes_oop_2.Entity;

import java.util.Objects;

public class Card {
    protected String name;
    protected int id;
    private static int count = 0;
    protected String image;

    public Card(String name, String image){
        this.name = name;
        this.image = image;
        this.id = count++;
    }

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Card card = (Card) o;
        return id == card.id;
    }
}
