package com.mvz.plants;

import com.mvz.Plant;
import com.mvz.Tile;
import com.mvz.Character;
import com.mvz.Zombie;

public class Tanglekelp extends Plant {
    public Tanglekelp(Tile tile) {
        super("Tangle kelp", 80, 9999.0f,  9999.0f, 0.0f, 1, 20, true, tile);
    }

    // hanya attack zommbie pada tile tsb
    // Aquatic, tidak bisa ditaruh di atas lilypad
    public void action(){
        Tile currentTile = this.getTile();

        for (Character owner : currentTile.getOwners()) {
            // check if the owner is a zombie
            if (owner instanceof Zombie && ((Zombie) owner).getHealth()>1) {
                // decrease the health of the zombie by the attack damage of the plant 
                Float damage = this.getAD();
                owner.decreaseHealth(damage);
            }
        }
    }
}
