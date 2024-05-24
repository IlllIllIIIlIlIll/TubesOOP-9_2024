package com.mvz.plants;

import com.mvz.Plant;

public class Wallnut extends Plant {
    public static long lastPlantedTime;

    public Wallnut(Integer x, Integer y) {
        super("Wall nut", 50, 1000.0f,  0.0f, 0.0f, 0, 20, false, x, y);
    }

    public Wallnut() {
        super("Wall nut", 50, 1000.0f,  0.0f, 0.0f, 0, 20, false);
    }
    
    public boolean isReadyToBePlanted() {
        long currentTime = System.currentTimeMillis();
        long elapsedTime = (currentTime - lastPlantedTime)/1000;
        return elapsedTime >= getCD();
    }

    public void setLastPlantedTime(long time) {
        if (time > lastPlantedTime) lastPlantedTime = time;
    }
    
    // do nothing
    public void action(){

    }
}
