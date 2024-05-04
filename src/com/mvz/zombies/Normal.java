package com.mvz.zombies;

import com.mvz.Zombie;
import com.mvz.Tile;

public class Normal extends Zombie {
    public Normal(Tile tile) {
        super("Normal zombie", 125, 100.0f, 1.0f, 5.0f, false, tile);
    }

    // how to attack plant within x - 1
    public void action(){

    }
}
