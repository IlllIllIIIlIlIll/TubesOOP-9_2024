package com.mvz.plants;

import com.mvz.Plant;
import com.mvz.Tile;

public class Peashooter extends Plant {
    public Peashooter(Tile tile) {
        super("Peashooter", 100, 100.0f,  25.0f, 4.0f, -1, 10, false, tile);
    }

    // how to attack zombie
    public void action(){

    }
}
