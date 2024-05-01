package com.mvz.plants;

import com.mvz.Plant;
import com.mvz.Tile;

public class Repeater extends Plant {
    public Repeater(Tile tile) {
        super("Repeater", 200, 100,  25.0f, 2.0f, -1, 10, false, tile);
    }

    // same as peashooter (just 2x atkspd)
    public void action(){

    }
}
