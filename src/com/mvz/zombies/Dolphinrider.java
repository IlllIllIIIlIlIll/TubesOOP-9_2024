package com.mvz.zombies;

import com.mvz.Zombie;
import com.mvz.Tile;

public class Dolphinrider extends Zombie {
    private Tile tile;

    public Dolphinrider(Tile tile) {
        super("Dolphin rider", 175, 100.0f, 1.0f, 5.0f, true, tile);
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
