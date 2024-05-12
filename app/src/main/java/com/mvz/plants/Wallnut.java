package com.mvz.plants;

import com.mvz.Plant;
import com.mvz.Tile;

public class Wallnut extends Plant {
    public Wallnut(Tile tile) {
        super("Wall nut", 50, 1000.0f,  0.0f, 0.0f, 0, 20, false, tile);
    }

    public Wallnut() {
        super("Wall nut", 50, 1000.0f,  0.0f, 0.0f, 0, 20, false);
    }
    
    // do nothing
    public void action(){

    }
}
