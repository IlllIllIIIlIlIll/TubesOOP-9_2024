package com.mvz.zombies;

import com.mvz.Zombie;

public class Dolphinrider extends Zombie {
    public Dolphinrider(Integer x, Integer y) {
        super("Dolphin rider", 175.0f, 100.0f, 1.0f, 5.0f, true, x, y);
    }

    // how to attack plant within x - 1
    public void action(){
        // if (x > 0) {
        //     currentTile.setX(x - 1);
        //     Tile nextLeftTile = getTile();
        //     for (com.mvz.Character owner : nextLeftTile.getOwners()) {
        //         if (owner instanceof com.mvz.Plant) {
        //             float damage = this.getAD();
        //             owner.decreaseHealth(damage);
        //             return;
        //         }
        //     }
        // }
    }
}
