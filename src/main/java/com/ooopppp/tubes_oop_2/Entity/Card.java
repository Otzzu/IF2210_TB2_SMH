package com.ooopppp.tubes_oop_2.Entity;

import java.util.Objects;

public class Card {
    private String name;
    private int id;
    private static int count = 0;

    public Card(String name){
        this.name = name;
        this.id = count++;
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
