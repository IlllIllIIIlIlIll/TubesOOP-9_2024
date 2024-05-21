package com.mvz;

import java.util.Scanner;

import com.mvz.plants.LandPlantFactory;
import com.mvz.plants.WaterPlantFactory;

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

    // create a new instance of plant
    public Plant createThePlant(String input, Tile tile) {
        Plant plantToPlant = null;
        for (Plant tumbuhan : deck.getPlants()) {
            if (tumbuhan.getName().toLowerCase().equals(input.toLowerCase())) {
                plantToPlant = tumbuhan;
                break;
            }
        }
        if (plantToPlant != null) {
            if (plantToPlant.isAquatic()) {
                WaterPlantFactory waterPlantFact = new WaterPlantFactory();
                return waterPlantFact.createPlant(plantToPlant.getName(), tile);
            } else {
                LandPlantFactory landPlantFactory = new LandPlantFactory();
                return landPlantFactory.createPlant(plantToPlant.getName(), tile);
            }
        } else return null;
    }
}