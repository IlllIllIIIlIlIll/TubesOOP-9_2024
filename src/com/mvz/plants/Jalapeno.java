package com.mvz.plants;

import com.mvz.Plant;
import com.mvz.Tile;

public class Jalapeno extends Plant {
    public Jalapeno(Tile tile) {
        super("Jalapeno", 125, 9999,  1800.0f, 0.0f, -1, 35, false, tile);
    }

    // attacks all zombie in the same y    
    public void action(){

    }
}
