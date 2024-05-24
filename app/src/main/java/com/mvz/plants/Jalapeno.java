package com.mvz.plants;

import com.mvz.Plant;

// Class representing the Jalapeno plant in the game, inheriting from the Plant class
public class Jalapeno extends Plant {
    // Static variable to track the last time a Jalapeno was planted
    public static long lastPlantedTime;

    // Constructor for creating a Jalapeno plant at a specific position
    public Jalapeno(Integer x, Integer y) {
        super("Jalapeno", 125, 9999.0f,  1800.0f, 0.0f, 9, 35, false, x, y);
    }

    // Default constructor for creating a Jalapeno plant without specifying a position
    public Jalapeno() {
        super("Jalapeno", 125, 9999.0f,  1800.0f, 0.0f, 9, 35, false);
    }

    // Method to check if the Jalapeno is ready to be planted based on cooldown time
    public boolean isReadyToBePlanted() {
        long currentTime = System.currentTimeMillis();
        long elapsedTime = currentTime - lastPlantedTime;
        return elapsedTime >= getCD();
    }

    // Method to update the last planted time if the current time is greater
    public void setLastPlantedTime(long time) {
        if (time > lastPlantedTime) lastPlantedTime = time;
    }
    
    // Method to perform the Jalapeno's action, decreasing health and printing a humorous message
    public void action(){
        decreaseHealth(health);
        System.out.println("Dadah bang, aku jalapeno pamit undur diri");
    }
}
