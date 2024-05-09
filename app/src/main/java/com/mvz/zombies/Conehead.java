package com.mvz.zombies;

import com.mvz.Zombie;
import com.mvz.Tile;

public class Conehead extends Zombie {
    public Conehead(Tile tile) {
        super("Conehead zombie", 250.0f, 100.0f, 1.0f, 5.0f, false, tile);
    }

    // how to attack plant within x - 1
    public void action(){

    }
}
