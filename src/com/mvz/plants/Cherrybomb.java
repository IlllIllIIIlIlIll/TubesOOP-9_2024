package com.mvz.plants;

import com.mvz.Plant;
import com.mvz.Tile;

public class Cherrybomb extends Plant {
    public Cherrybomb(Tile tile) {
        super("Cherry bomb", 150, 9999,  1800.0f, 0.0f, 9, 30, false, tile);
    }

    // immediately attack zombies within the tile and surrounding +1 tile (3x3)
    public void action(){

    }
}
