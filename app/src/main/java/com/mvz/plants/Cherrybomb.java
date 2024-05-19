package com.mvz.plants;

import com.mvz.Plant;

public class Cherrybomb extends Plant {
    public static long lastPlantedTime;
    
    public Cherrybomb(Integer x, Integer y) {
        super("Cherrybomb", 150, 100.0f,  5000.0f, 0.0f, 3, 30, false, x, y);
    }

    public Cherrybomb() {
        super("Cherrybomb", 150, 100.0f,  5000.0f, 0.0f, 3, 30, false);
    }

    public boolean isReadyToBePlanted() {
        long currentTime = System.currentTimeMillis();
        long elapsedTime = currentTime - lastPlantedTime;
        return elapsedTime >= getCD();
    }

    public void setLastPlantedTime(long time) {
        if (time > lastPlantedTime) lastPlantedTime = time;
    }
    
    public void action(){
        decreaseHealth(health);
        System.out.println("Bang aku udah meledak karena kegendutan");
    }
}
