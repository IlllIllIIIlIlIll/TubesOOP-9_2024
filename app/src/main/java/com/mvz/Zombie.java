package com.mvz;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

// Abstract class Zombie extending the Character class
public abstract class Zombie extends Character {
    private Float movement_speed;   
    private boolean isChilled;
    private boolean canMove;
    private transient ScheduledExecutorService movementExecutorService; 

    // Time remaining for the zombie to be able to move again
    private long timeRemainingToMove;

    // Constructor initializing the zombie with its attributes
    public Zombie(String name, Float health, Float attack_damage, Float attack_speed, Float movement_speed, boolean isAquatic, Integer x, Integer y) {
        super(name, health, isAquatic, attack_speed, attack_damage, x, y);
        this.movement_speed = movement_speed;
        isChilled = false;
        canMove = true;
        timeRemainingToMove = Math.round(movement_speed * 1000);
        initScheduledExecutors();   // Initialize the scheduled executors for movement
    }

    // Initialize the scheduled executor service and start the movement timer
    private void initScheduledExecutors() {
        movementExecutorService = Executors.newSingleThreadScheduledExecutor();
        startMovementTimer();
    }

    // Getter for movement speed
    public Float getMS() {
        return movement_speed;
    }

    // Getter for chilled status
    public boolean getCH() {
        return isChilled;
    }

    // Getter for canMove status
    public boolean getCM() {
        return canMove;
    }

    // Toggle the chilled status
    public void setCH() {
        isChilled = !isChilled;
    }

    // Toggle the canMove status
    public void setCM() {
        canMove = !canMove;
    }

    // Set the canMove status explicitly
    public void setCM(boolean canMove) {
        this.canMove = canMove;
    }
    
    // Set the movement speed
    public void setMSD(Float movement_speed) {
        this.movement_speed = movement_speed;
    }

    // Getter for time remaining to move
    public long getTimeRemainingToMove() {
        return timeRemainingToMove;
    }

    // Setter for time remaining to move
    public void setTimeRemainingToMove(long timeRemainingToMove) {
        this.timeRemainingToMove = timeRemainingToMove;
    }

    // Start a timer that sets canMove to true at fixed intervals based on movement speed
    private void startMovementTimer() {
        movementExecutorService.scheduleAtFixedRate(() -> {
            canMove = true;
        }, timeRemainingToMove, Math.round(movement_speed * 1000), TimeUnit.MILLISECONDS);
    }

    // Reset the movement timer by shutting down the current executor and creating a new one
    public void resetMovementTimer() {
        movementExecutorService.shutdownNow();
        movementExecutorService = Executors.newSingleThreadScheduledExecutor();
        startMovementTimer();
    }

    // Ensure the scheduled executors are initialized
    public void initZombieScheduledExecutors() {
        initScheduledExecutors();
    }
}
