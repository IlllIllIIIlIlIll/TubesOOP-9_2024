package com.mvz;

import java.util.Scanner;

public class Player {
    private String name;
    private Deck deck;

    // Constructor initializes the player's name and creates a new Deck
    public Player(String name){
        this.name = name;
        this.deck = new Deck();
        
    }

    // Method to allow the player to customize their deck
    public void customizeDeck(Scanner scanner) {
        deck.deckMenu(scanner);
    }

    // Getter method for the player's deck
    public Deck getDeck(){
        return deck;
    }

    // Setter method to update the player's deck
    public void setDeck(Deck deck){
        this.deck = deck;
    }

    // Getter method for the player's name
    public String getName(){
        return name;
    }

}