package com.mvz.plants;

import com.mvz.Plant;
import com.mvz.Character;
import com.mvz.Zombie;

public class Jalapeno extends Plant {
    public Jalapeno(Integer x, Integer y) {
        super("Jalapeno", 125, 9999.0f,  1800.0f, 0.0f, -1, 35, false, x, y);
    }

    public Jalapeno() {
        super("Jalapeno", 125, 9999.0f,  1800.0f, 0.0f, -1, 35, false);
    }

    // attacks all zombie in the same y    
    public void action(){
        // Tile currentTile = this.getTile();
        // int y = currentTile.getY();

        // for (Character owner : currentTile.getOwners()) {
        //     if (owner instanceof Zombie && ((Zombie) owner).getTile().getY() == y) {
        //         // Attacking the zombie
        //         Float damage = this.getAD();
        //         owner.decreaseHealth(damage);
        //     }
        // }
    }
}
