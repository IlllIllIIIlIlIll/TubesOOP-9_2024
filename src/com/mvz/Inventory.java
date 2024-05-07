package com.mvz;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private static Inventory instance;
    private List<Plant> plants;

    private Inventory() {
        plants = new ArrayList<>();
    }

    public static Inventory getInstance() {
        if (instance == null) {
            instance = new Inventory();
        }
        return instance;
    }

    public void setPlants(List<Plant> plants) {
        this.plants = plants;
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
