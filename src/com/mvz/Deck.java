package com.mvz;

import java.util.ArrayList;
import java.util.List;

public class Deck {
    private static Deck instance;
    private static List<Plant> plants;

    private Deck() {
        plants = new ArrayList<>();
    }

    public static Deck getInstance() {
        if (instance == null) {
            instance = new Deck();
        }
        return instance;
    }

    public void addPlant(Plant plant) {
        plants.add(plant);
    }

    public List<Plant> getPlants() {
        return plants;
    }
    
    public void printDeck() {
        System.out.println("Deck:");
        for (Plant plant : plants) {
            System.out.println(plant.getName());
        }
    }
}
