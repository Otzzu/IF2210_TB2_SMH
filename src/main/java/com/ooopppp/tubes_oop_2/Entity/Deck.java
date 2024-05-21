package com.ooopppp.tubes_oop_2.Entity;

import java.util.*;

public class Deck {
    private List<Card> activeDeck;
    private List<Card> allDeck;
    private String[] cardTypes = {
            "HIU_DARAT", "SAPI", "DOMBA", "KUDA", "AYAM", "BERUANG",
            "BIJI_JAGUNG", "BIJI_LABU", "BIJI_STROBERI", "ACCELERATE",
            "DELAY", "INSTANT_HARVEST", "DESTROY", "PROTECT", "TRAP",
            "SIRIP_HIU", "SUSU", "DAGING_DOMBA", "DAGING_KUDA", "TELUR",
            "DAGING_BERUANG", "JAGUNG", "LABU", "STROBERI"
    };
    private String[] highFreqCard = {
            "BIJI_JAGUNG", "BIJI_LABU", "BIJI_STROBERI", "ACCELERATE",
            "INSTANT_HARVEST", "TRAP",  "SAPI", "AYAM"
    };

    public Deck() {
        activeDeck = new ArrayList<>(6);
        allDeck = new ArrayList<>(40);
    }

    public List<Card> getActiveDeck() {
        return activeDeck;
    }

    public List<Card> getAllDeck() {
        return allDeck;
    }

    public void generateDeckBaseOnTurn (int turn){
        int deckSize = 40 - (turn*4);
        generateDeck(deckSize);
    }

    public List<Card> shuffleCard(int numCard){
        Collections.shuffle(allDeck);
        if (allDeck.size() < numCard){
            numCard = allDeck.size();
        }

        return new ArrayList<>(allDeck.subList(0, numCard));
    }

    public void moveToActiveDeck(List<Card> chooseCard){
        for (Card c: chooseCard){
            allDeck.remove(c);
            activeDeck.add(c);
        }
    }

    public void generateDeck(int deckSize) {
        allDeck.clear();
        CardFactory cardFactory = new CardFactory();
        Random random = new Random();
        List<String> deckList = new ArrayList<>(deckSize);

        if (deckSize > highFreqCard.length + 7){
            for (int i = 0; i < highFreqCard.length; i++){
                String choose = highFreqCard[random.nextInt(highFreqCard.length - 1)];
                deckList.add(choose);
            }

            for (int i = 0; i < deckSize - highFreqCard.length; i++){
                String choose = cardTypes[random.nextInt(cardTypes.length - 1)];
                deckList.add(choose);
            }
        } else {
            for (int i = 0; i < deckSize; i++){
                String choose = cardTypes[random.nextInt(cardTypes.length - 1)];
                deckList.add(choose);
            }
        }


        for (String cardType: deckList){
            allDeck.add(cardFactory.createCard(cardType));
        }
    }
}
