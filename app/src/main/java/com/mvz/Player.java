package com.mvz;

public class Player {
    private String name;
    private Deck deck;

    public Player(String name){
        this.name = name;
        this.deck = new Deck();
    }

    public void customizeDeck() {
        // Logic for the player to customize their deck
    }

    public Deck getDeck(){
        return deck;
    }

    public void setDeck(Deck deck){
        this.deck = deck;
    }

    public String getName(){
        return name;
    }

}