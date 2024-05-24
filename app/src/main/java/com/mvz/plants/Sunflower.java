package com.mvz.plants;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.mvz.Plant;
import com.mvz.Sun;

// Represents a Sunflower plant in the game, which generates suns over time
public class Sunflower extends Plant {
    // Static variable to track the last time a Sunflower was planted
    public static long lastPlantedTime;

    // Executor service for scheduling the generation of suns
    private transient ScheduledExecutorService executorService;
    // Flag indicating whether the Sunflower is ready to generate suns
    private boolean readyToGenerateSun = true;

    // Constructor for creating a Sunflower plant at a specific position
    public Sunflower(Integer x, Integer y) {
        super("Sunflower", 50, 100.0f,  0.0f, 0.0f, 0, 10, false, x, y);
        initScheduledExecutors();
    }

    // Default constructor for creating a Sunflower plant without specifying a position
    public Sunflower() {
        super("Sunflower", 50, 100.0f,  0.0f, 0.0f, 0, 10, false);
    }

    // Initializes the scheduled executor service for generating suns
    private void initScheduledExecutors() {
        executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(() -> {
            synchronized (Sun.class) {
                if (readyToGenerateSun) {
                    Sun.increaseSun(25);    // Generates 25 suns
                    readyToGenerateSun = false;   // Prevents immediate re-generation
                    executorService.schedule(() -> readyToGenerateSun = true, 3, TimeUnit.SECONDS);     // Resets after 3 seconds
                }
            }
        }, 0, 1, TimeUnit.SECONDS);     // Starts immediately, then every second
    }

    // Method to check if the Sunflower is ready to be planted based on cooldown time
    public boolean isReadyToBePlanted() {
        long currentTime = System.currentTimeMillis();
        long elapsedTime = currentTime - lastPlantedTime;
        return elapsedTime >= getCD();
    }

    // Method to update the last planted time if the current time is greater
    public void setLastPlantedTime(long time) {
        if (time > lastPlantedTime) lastPlantedTime = time;
    }
    
    // Define how the Sunflower affects gameplay
    public void action() {
        
    }

    // Method to initialize the executor service for the Sunflower, allowing manual control over its sun generation
    public void initSunflowerExecutorService() {
        initScheduledExecutors();
    }
}
