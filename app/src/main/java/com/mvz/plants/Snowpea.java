package com.mvz.plants;

import com.mvz.Plant;

// Represents a Snowpea plant in the game, known for its projectile attack
public class Snowpea extends Plant {
    // Static variable to track the last time a Snowpea was planted
    public static long lastPlantedTime;

    // Constructor for creating a Snowpea plant at a specific position
    public Snowpea(Integer x, Integer y) {
        super("Snow pea", 175, 100.0f,  25.0f, 4.0f, -1, 10, false, x, y);
    }

    // Default constructor for creating a Snowpea plant without specifying a position
    public Snowpea() {
        super("Snow pea", 175, 100.0f,  25.0f, 4.0f, -1, 10, false);
    }

    // Method to check if the Snowpea is ready to be planted based on cooldown time
    public boolean isReadyToBePlanted() {
        long currentTime = System.currentTimeMillis();
        long elapsedTime = currentTime - lastPlantedTime;
        return elapsedTime >= getCD();
    }

    // Method to update the last planted time if the current time is greater
    public void setLastPlantedTime(long time) {
        if (time > lastPlantedTime) lastPlantedTime = time;
    }
    
    // Define how the Snowpea attacks zombies
    public void action(){
    
    }
}
