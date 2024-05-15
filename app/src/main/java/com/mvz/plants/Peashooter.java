package com.mvz.plants;

import com.mvz.Plant;
import com.mvz.Zombie;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.mvz.Character;

public class Peashooter extends Plant {

    private ScheduledExecutorService executorService;
    public Peashooter(Integer x, Integer y) {
        super("Peashooter", 100, 100.0f,  25.0f, 4.0f, -1, 10, false, x, y);
        executorService = Executors.newSingleThreadScheduledExecutor();
    }

    public Peashooter() {
        super("Peashooter", 100, 100.0f,  25.0f, 4.0f, -1, 10, false);
        executorService = Executors.newSingleThreadScheduledExecutor();
    }
    
    // how to attack zombie
    public void action(){
        // // get peashooter's position  
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
        //     }   , 0, 4, TimeUnit.SECONDS); 

        }
}
