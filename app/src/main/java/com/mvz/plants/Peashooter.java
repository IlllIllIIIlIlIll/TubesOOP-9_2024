package com.mvz.plants;

import com.mvz.Plant;

public class Peashooter extends Plant {
    public static long lastPlantedTime;

    public Peashooter(Integer x, Integer y) {
        super("Peashooter", 100, 100.0f,  25.0f, 4.0f, -1, 10, false, x, y);
    }

    public Peashooter() {
        super("Peashooter", 100, 100.0f,  25.0f, 4.0f, -1, 10, false);
    }
    
    public boolean isReadyToBePlanted() {
        long currentTime = System.currentTimeMillis();
        long elapsedTime = (currentTime - lastPlantedTime)/1000;
        return elapsedTime >= getCD();
    }

    public void setLastPlantedTime(long time) {
        if (time > lastPlantedTime) lastPlantedTime = time;
    }
    
    // how to attack zombie
    public void action(){

        }
}
