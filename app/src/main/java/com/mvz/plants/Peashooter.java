package com.mvz.plants;

import com.mvz.Plant;

// Represents a Peashooter plant in the game, capable of attacking zombies
public class Peashooter extends Plant {
    // Static variable to track the last time a Peashooter was planted
    public static long lastPlantedTime;

    // Constructor for creating a Peashooter plant at a specific position
    public Peashooter(Integer x, Integer y) {
        super("Peashooter", 100, 100.0f,  25.0f, 4.0f, -1, 10, false, x, y);
    }

    // Default constructor for creating a Peashooter plant without specifying a position
    public Peashooter() {
        super("Peashooter", 100, 100.0f,  25.0f, 4.0f, -1, 10, false);
    }
    
    // Method to check if the Peashooter is ready to be planted based on cooldown time
    public boolean isReadyToBePlanted() {
        long currentTime = System.currentTimeMillis();
        long elapsedTime = currentTime - lastPlantedTime;
        return elapsedTime >= getCD();
    }

    // Method to update the last planted time if the current time is greater
    public void setLastPlantedTime(long time) {
        if (time > lastPlantedTime) lastPlantedTime = time;
    }
    
    // Define how the Peashooter attacks zombies
    public void action(){

        }
}
