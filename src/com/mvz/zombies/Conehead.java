package com.mvz.zombies;

import com.mvz.Zombie;
import com.mvz.Tile;

public class Conehead extends Zombie {
    private Tile tile;

    public Conehead(Tile tile) {
        super("Conehead zombie", 250, 100.0f, 1.0f, 5.0f, false, tile);
    }

    public Tile getTile(){
        return this.tile;
    }

    // how to attack plant within x - 1
    public void action(){
        Tile currentTile = getTile();
        int x = currentTile.getX();
        int y = currentTile.getY();

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
