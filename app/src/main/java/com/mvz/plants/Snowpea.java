package com.mvz.plants;

import com.mvz.Plant;
import com.mvz.Tile;

public class Snowpea extends Plant {
    public Snowpea(Tile tile) {
        super("Snow pea", 175, 100.0f,  25.0f, 4.0f, -1, 10, false, tile);
    }

    // reduce 50% of nearest zombie attackspd and movementspd that has greater x
    // lasting for 3 secs after latest hit
    public void action(){

    }
}
