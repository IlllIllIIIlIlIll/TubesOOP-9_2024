package com.mvz.zombies;

import com.mvz.Zombie;

public class Duckytube extends Zombie {
    public Duckytube(Integer x, Integer y) {
        super("Ducky tube", 125.0f, 100.0f, 1.0f, 5.0f, true, x, y);
    }

    public Tile getTile(){
        return this.tile;
    }

    // how to attack plant within x - 1
    public void action(){
        Tile currentTile = getTile();
        int x = currentTile.getX();

        if (x > 0) {
            Tile leftTile = getTile();

            for (com.mvz.Character owner : leftTile.getOwners()) {
                if (owner instanceof com.mvz.Plant) {
                    float damage = this.getAD();
                    owner.decreaseHealth(damage);
                    return;
                }
            }
        }
    }
}
