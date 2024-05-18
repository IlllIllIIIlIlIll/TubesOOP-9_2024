package com.mvz;

import java.util.Scanner;

public class Player {
    private String name;
    private Deck deck;

    public Player(String name){
        this.name = name;
        this.deck = new Deck();
        
    }

    public void customizeDeck(Scanner scanner) {
        deck.deckMenu(scanner);
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