package com.mvz.zombies;

import com.mvz.Zombie;
import com.mvz.Tile;

public class Duckytube extends Zombie {
    public Duckytube(Tile tile) {
        super("Ducky tube", 125, 100.0f, 1.0f, 5.0f, true, tile);
    }

    // how to attack plant within x - 1
    public void action(){

    }
}
