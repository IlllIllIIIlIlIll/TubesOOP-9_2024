package com.mvz.plants;

import com.mvz.Plant;

public class Jalapeno extends Plant {
    public static long lastPlantedTime;

    public Jalapeno(Integer x, Integer y) {
        super("Jalapeno", 125, 9999.0f,  1800.0f, 0.0f, 9, 35, false, x, y);
    }

    public Jalapeno() {
        super("Jalapeno", 125, 9999.0f,  1800.0f, 0.0f, 9, 35, false);
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
