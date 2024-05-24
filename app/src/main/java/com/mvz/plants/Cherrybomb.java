package com.mvz.plants;

import com.mvz.Plant;

// Class representing the Cherrybomb plant in the game, inheriting from the Plant class
public class Cherrybomb extends Plant {
    // Static variable to track the last time a Cherrybomb was planted
    public static long lastPlantedTime;
    
    // Constructor for creating a Cherrybomb plant at a specific position
    public Cherrybomb(Integer x, Integer y) {
        super("Cherrybomb", 150, 100.0f,  5000.0f, 0.0f, 3, 30, false, x, y);
    }

    // Default constructor for creating a Cherrybomb plant without specifying a position
    public Cherrybomb() {
        super("Cherrybomb", 150, 100.0f,  5000.0f, 0.0f, 3, 30, false);
    }

    // Method to check if the Cherrybomb is ready to be planted based on cooldown time
    public boolean isReadyToBePlanted() {
        long currentTime = System.currentTimeMillis();
        long elapsedTime = currentTime - lastPlantedTime;
        return elapsedTime >= getCD();
    }

    // Method to update the last planted time if the current time is greater
    public void setLastPlantedTime(long time) {
        if (time > lastPlantedTime) lastPlantedTime = time;
    }
    
    // Method to perform the Cherrybomb's action, decreasing health and printing a message
    public void action(){
        decreaseHealth(health);
        System.out.println("Bang aku udah meledak karena kegendutan");
    }
}
