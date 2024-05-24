package com.mvz.plants;

import com.mvz.Plant;

// Represents a Squash plant in the game, known for its defensive capabilities
public class Squash extends Plant {
    // Static variable to track the last time a Squash was planted
    public static long lastPlantedTime;

    // Constructor for creating a Squash plant at a specific position
    public Squash(Integer x, Integer y) {
        super("Squash", 50, 100.0f,  5000.0f, 0.0f, 1, 20, false, x, y);
    }

    // Default constructor for creating a Squash plant without specifying a position
    public Squash() {
        super("Squash", 50, 100.0f,  5000.0f, 0.0f, 1, 20, false);
    }
    
    // Method to check if the Squash is ready to be planted based on cooldown time
    public boolean isReadyToBePlanted() {
        long currentTime = System.currentTimeMillis();
        long elapsedTime = currentTime - lastPlantedTime;
        return elapsedTime >= getCD();
    }

    // Method to update the last planted time if the current time is greater
    public void setLastPlantedTime(long time) {
        if (time > lastPlantedTime) lastPlantedTime = time;
    }
    
    // Method to perform the Squash's action, decreasing its own health and printing a message
    public void action(){
        decreaseHealth(health);
        System.out.println("Zombienya udah aku tiban bang, cabut ahh");
    }
}