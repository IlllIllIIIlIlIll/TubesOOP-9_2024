package com.mvz.plants;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.mvz.Plant;
import com.mvz.Zombie;
import com.mvz.Character;

public class Snowpea extends Plant {
     private ScheduledExecutorService executorService;
    public Snowpea(Integer x, Integer y) {
        super("Snow pea", 175, 100.0f,  25.0f, 4.0f, -1, 10, false, x, y);
        executorService = Executors.newSingleThreadScheduledExecutor();
    }

    public Snowpea() {
        super("Snow pea", 175, 100.0f,  25.0f, 4.0f, -1, 10, false);
        executorService = Executors.newSingleThreadScheduledExecutor();
    }

    // reduce 50% of nearest zombie attackspd and movementspd that has greater x
    // lasting for 3 secs after latest hit
    public void action(){
        // executorService.scheduleAtFixedRate(() -> {
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
        //                 ((Zombie) owner).setCH();
        //                 owner.decreaseHealth(damage);
        //             }
        //             break;
        //             }
        //         }
        //     }   , 0, 4, TimeUnit.SECONDS); 

        }
}
