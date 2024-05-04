package com.mvz;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private static Inventory instance;
    public List<Plant> plants;

    private Inventory() {
        plants = new ArrayList<>();
    }

    public static Inventory getInstance() {
        if (instance == null) {
            instance = new Inventory();
        }
        return instance;
    }

    public void addPlant(Plant plant) {
        plants.add(plant);
    }

    public List<Plant> getPlants() {
        return plants;
    }
    
    public void printInventory() {
        System.out.println("Inventory:");
        for (Plant plant : plants) {
            System.out.println(plant.getName());
        }
    }
}
