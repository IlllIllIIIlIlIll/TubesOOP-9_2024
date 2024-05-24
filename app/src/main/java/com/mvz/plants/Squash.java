package com.mvz.plants;

import com.mvz.Plant;

public class Squash extends Plant {
    public static long lastPlantedTime;

    public Squash(Integer x, Integer y) {
        super("Squash", 50, 100.0f,  5000.0f, 0.0f, 1, 20, false, x, y);
    }

    public Squash() {
        super("Squash", 50, 100.0f,  5000.0f, 0.0f, 1, 20, false);
    }
    
    public boolean isReadyToBePlanted() {
        long currentTime = System.currentTimeMillis();
        long elapsedTime = (currentTime - lastPlantedTime)/1000;
        return elapsedTime >= getCD();
    }

    public void setLastPlantedTime(long time) {
        if (time > lastPlantedTime) lastPlantedTime = time;
    }
    
    public void action(){
        decreaseHealth(health);
    }
}