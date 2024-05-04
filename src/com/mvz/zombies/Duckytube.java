package com.mvz.zombies;

import com.mvz.Zombie;
import com.mvz.Tile;

public class Duckytube extends Zombie {
    private Tile tile;
    
    public Duckytube(Tile tile) {
        super("Ducky tube", 125, 100.0f, 1.0f, 5.0f, true, tile);
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
