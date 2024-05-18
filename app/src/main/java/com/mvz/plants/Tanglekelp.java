package com.mvz.plants;

import com.mvz.Plant;
import com.mvz.Tile;
import com.mvz.Character;
import com.mvz.Zombie;

public class Tanglekelp extends Plant {
    public static long lastPlantedTime;

    public Tanglekelp(Integer x, Integer y) {
        super("Tangle kelp", 80, 100.0f,  5000.0f, 0.0f, 1, 20, true, x, y);
    }

    public Tanglekelp() {
        super("Tangle kelp", 80, 100.0f,  5000.0f, 0.0f, 1, 20, true);
    }

    public boolean isReadyToBePlanted() {
        long currentTime = System.currentTimeMillis();
        long elapsedTime = currentTime - lastPlantedTime;
        return elapsedTime >= getCD();
    }

    public void setLastPlantedTime(long time) {
        if (time > lastPlantedTime) lastPlantedTime = time;
    }

    // hanya attack zommbie pada tile tsb
    // Aquatic, tidak bisa ditaruh di atas lilypad
    public void action(){
        decreaseHealth(health);
        System.out.println("Selain menarik perhatian, aku juga menarik zombie");
    }
}
