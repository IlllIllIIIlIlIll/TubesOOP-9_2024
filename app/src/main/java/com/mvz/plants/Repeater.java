package com.mvz.plants;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.mvz.Plant;
import com.mvz.Zombie;
import com.mvz.Character;

public class Repeater extends Plant {
    public static long lastPlantedTime;
    private ScheduledExecutorService executorService;

    public Repeater(Integer x, Integer y) {
        super("Repeater", 200, 100.0f,  25.0f, 2.0f, -1, 10, false, x, y);
        executorService = Executors.newSingleThreadScheduledExecutor();
    }

    public Repeater() {
        super("Repeater", 200, 100.0f,  25.0f, 2.0f, -1, 10, false);
        executorService = Executors.newSingleThreadScheduledExecutor();
    }

    public boolean isReadyToBePlanted() {
        long currentTime = System.currentTimeMillis();
        long elapsedTime = currentTime - lastPlantedTime;
        return elapsedTime >= getCD();
    }

    public void setLastPlantedTime(long time) {
        if (time > lastPlantedTime) lastPlantedTime = time;
    }
    
    // same as peashooter (just 2x atkspd)
    public void action(){
        // executorService.scheduleAtFixedRate(() ->{
        //     Tile currentTile = getTile();
        //     int x = currentTile.getX();
        //     int y = currentTile.getY();
            
        //     // same x and 
        //     for (int i = x ; i <= 9; i++) {
        //         Tile targetTile = getTile();

        //         for (Character owner : targetTile.getOwners()) {
        //             if (owner instanceof Zombie && ((Zombie) owner).getTile().getY() == y) {
        //             // Attacking the zombie
        //                 Float damage = this.getAD();
        //                 owner.decreaseHealth(damage);
        //             }
        //             break;
        //             }
        //         }
        //     }   , 0, 2, TimeUnit.SECONDS); 

        }
    

    }

