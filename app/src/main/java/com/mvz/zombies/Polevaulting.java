package com.mvz.zombies;

import com.mvz.Zombie;
import com.mvz.Tile;

public class Polevaulting extends Zombie {
    private Tile tile;

    public Polevaulting(Tile tile) {
        super("Pole vaulting", 175.0f, 100.0f, 1.0f, 5.0f, false, tile);
    }

    public Tile getTile(){
        return this.tile;
    }

    // how to attack plant within x - 1
    public void action(){
        Tile currentTile = getTile();
        int x = currentTile.getX();

        if (x > 0) {
            currentTile.setX(x - 1);
            Tile nextLeftTile = getTile();
            for (com.mvz.Character owner : nextLeftTile.getOwners()) {
                if (owner instanceof com.mvz.Plant) {
                    float damage = this.getAD();
                    owner.decreaseHealth(damage);
                    return;
                }
            }
        }
    }
}
