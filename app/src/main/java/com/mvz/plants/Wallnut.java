package com.mvz.plants;

import com.mvz.Plant;

// Represents a Wall Nut plant in the game, providing defense against zombies
public class Wallnut extends Plant {
    // Static variable to track the last time a Wall Nut was planted
    public static long lastPlantedTime;

    // Constructor for creating a Wall Nut plant at a specific position
    public Wallnut(Integer x, Integer y) {
        super("Wall nut", 50, 1000.0f,  0.0f, 0.0f, 0, 20, false, x, y);
    }

    // Default constructor for creating a Wall Nut plant without specifying a position
    public Wallnut() {
        super("Wall nut", 50, 1000.0f,  0.0f, 0.0f, 0, 20, false);
    }
    
    // Method to check if the Wall Nut is ready to be planted based on cooldown time
    public boolean isReadyToBePlanted() {
        long currentTime = System.currentTimeMillis();
        long elapsedTime = currentTime - lastPlantedTime;
        return elapsedTime >= getCD();
    }

    // Method to update the last planted time if the current time is greater
    public void setLastPlantedTime(long time) {
        if (time > lastPlantedTime) lastPlantedTime = time;
    }
    
    // Does nothing as Wallnut are typically passive defenses
    public void action(){

    }
}
