package com.mvz.zombies;

import com.mvz.Zombie;

public class Buckethead extends Zombie {

    public Buckethead(Integer x, Integer y) {
        super("Bucket head", 300.0f, 100.0f, 1.0f, 5.0f, false, x, y);
    }
    
    // how to attack plant within x - 1
    public void action(){
        // if (x > 0) {
        //     Tile leftTile = getTile();

        //     for (com.mvz.Character owner : leftTile.getOwners()) {
        //         if (owner instanceof com.mvz.Plant) {
        //             float damage = this.getAD();
        //             owner.decreaseHealth(damage);
        //             return;
        //         }
        //     }
        // }
    }
}
