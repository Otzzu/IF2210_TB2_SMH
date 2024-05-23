package com.ooopppp.tubes_oop_2.Entity;

import java.util.*;

public class Deck {
    private Card[] activeDeck;
    private List<Card> allDeck;
    private String[] cardTypes = {
            "HIU_DARAT", "SAPI", "DOMBA", "KUDA", "AYAM",
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
        activeDeck = new Card[6];
        allDeck = new ArrayList<>(40);
    }

    public Card[] getActiveDeck() {
        return activeDeck;
    }

    public List<Card> getAllDeck() {
        return allDeck;
    }

    public void generateDeckBaseOnTurn (int turn){
        int deckSize = 40 - (turn*4);
        generateDeck(deckSize);
    }

    public List<Card> shuffleCard(){
        int numCard = 0;

        for (int i = 0; i < 6; i++){
            if (activeDeck[i] == null){
                numCard++;
                if (numCard == 4){
                    break;
                }
            }
        }
        Collections.shuffle(allDeck);
        if (allDeck.size() < numCard){
            numCard = allDeck.size();
        }

        return new ArrayList<>(allDeck.subList(0, numCard));
    }

    public void moveToActiveDeck(List<Card> chooseCard){
        for (Card c: chooseCard){
            allDeck.remove(c);
            for (int i = 0; i < 6; i++){
                if (activeDeck[i] == null){
                    activeDeck[i] = c;
                    break;
                }
            }
        }
    }

    public void moveOneCardToActiveDeck(Card chooseCard, int i){
        activeDeck[i] = chooseCard;
    }
    
    public int getActiveDeckCount(){
        int count = 0;
        for (int i = 0; i < 6; i++){
            if(activeDeck[i] != null){
                count++;
            }
        }
        return count;
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

    public List<Product> getProductsFromActiveDeck() {
        List<Product> products = new ArrayList<>();
        for (Card card : activeDeck) {
            if (card instanceof Product) {
                products.add((Product) card);
            }
        }
        return products;
    }

    public boolean removeFromActiveDeck(Card card) {
        for (int i = 0; i < activeDeck.length; i++) {
            if (activeDeck[i] != null && activeDeck[i].equals(card)) {
                activeDeck[i] = null;
                return true;
            }
        }
        return false;
    }

}
