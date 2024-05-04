package com.mvz.plants;

import com.mvz.Plant;
import com.mvz.Tile;

public class Sunflower extends Plant {
    public Sunflower(Tile tile) {
        super("Sunflower", 50, 100,  0.0f, 0.0f, 0, 10, false, tile);
    }

    // produce 25 sun per 10 sec
    public void action(){

    }
}
