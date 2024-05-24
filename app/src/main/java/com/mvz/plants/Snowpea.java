package com.mvz.plants;

import com.mvz.Plant;

public class Snowpea extends Plant {
    public static long lastPlantedTime;

    public Snowpea(Integer x, Integer y) {
        super("Snow pea", 175, 100.0f,  25.0f, 4.0f, -1, 10, false, x, y);
    }

    public Snowpea() {
        super("Snow pea", 175, 100.0f,  25.0f, 4.0f, -1, 10, false);
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
