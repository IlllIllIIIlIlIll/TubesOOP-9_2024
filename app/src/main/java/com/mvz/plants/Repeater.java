package com.mvz.plants;

import com.mvz.Plant;

// Represents a Repeater plant in the game, capable of repeating its attack
public class Repeater extends Plant {
    // Static variable to track the last time a Repeater was planted
    public static long lastPlantedTime;

    // Constructor for creating a Repeater plant at a specific position
    public Repeater(Integer x, Integer y) {
        super("Repeater", 200, 100.0f,  25.0f, 2.0f, -1, 10, false, x, y);
    }

    // Default constructor for creating a Repeater plant without specifying a position
    public Repeater() {
        super("Repeater", 200, 100.0f,  25.0f, 2.0f, -1, 10, false);
    }

    // Method to check if the Repeater is ready to be planted based on cooldown time
    public boolean isReadyToBePlanted() {
        long currentTime = System.currentTimeMillis();
        long elapsedTime = currentTime - lastPlantedTime;
        return elapsedTime >= getCD();
    }

    // Method to update the last planted time if the current time is greater
    public void setLastPlantedTime(long time) {
        if (time > lastPlantedTime) lastPlantedTime = time;
    }
    
    // Define how the Repeater repeats its attack
    public void action(){

        }
    }

