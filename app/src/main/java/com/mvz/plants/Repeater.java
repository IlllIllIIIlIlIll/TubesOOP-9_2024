package com.mvz.plants;

import com.mvz.Plant;

public class Repeater extends Plant {
    public static long lastPlantedTime;

    public Repeater(Integer x, Integer y) {
        super("Repeater", 200, 100.0f,  25.0f, 2.0f, -1, 10, false, x, y);
    }

    public Repeater() {
        super("Repeater", 200, 100.0f,  25.0f, 2.0f, -1, 10, false);
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

        }
    }

