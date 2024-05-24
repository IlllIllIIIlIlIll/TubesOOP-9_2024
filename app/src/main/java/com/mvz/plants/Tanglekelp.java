package com.mvz.plants;

import com.mvz.Plant;

// Represents a Tangle Kelp plant in the game, known for attracting zombies
public class Tanglekelp extends Plant {
    // Static variable to track the last time a Tangle Kelp was planted
    public static long lastPlantedTime;

    // Constructor for creating a Tangle Kelp plant at a specific position
    public Tanglekelp(Integer x, Integer y) {
        super("Tangle kelp", 80, 100.0f,  5000.0f, 0.0f, 1, 20, true, x, y);
    }

    // Default constructor for creating a Tangle Kelp plant without specifying a position
    public Tanglekelp() {
        super("Tangle kelp", 80, 100.0f,  5000.0f, 0.0f, 1, 20, true);
    }

    // Method to check if the Tangle Kelp is ready to be planted based on cooldown time
    public boolean isReadyToBePlanted() {
        long currentTime = System.currentTimeMillis();
        long elapsedTime = currentTime - lastPlantedTime;
        return elapsedTime >= getCD();
    }

    // Method to update the last planted time if the current time is greater
    public void setLastPlantedTime(long time) {
        if (time > lastPlantedTime) lastPlantedTime = time;
    }
    
    // Method to perform the Tangle Kelp's action, decreasing its own health and printing a message
    public void action(){
        decreaseHealth(health);
        System.out.println("Selain menarik perhatian, aku juga menarik zombie");
    }
}
