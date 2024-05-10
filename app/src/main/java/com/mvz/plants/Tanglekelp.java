package com.mvz.plants;

import com.mvz.Plant;
import com.mvz.Tile;

public class Tanglekelp extends Plant {
    public Tanglekelp(Tile tile) {
        super("Tangle kelp", 80, 9999.0f,  9999.0f, 0.0f, 1, 20, true, tile);
    }

    public Tanglekelp() {
        super("Tangle kelp", 80, 9999.0f,  9999.0f, 0.0f, 1, 20, true);
    }

    // hanya attack zommbie pada tile tsb
    // Aquatic, tidak bisa ditaruh di atas lilypad
    public void action(){

    }
}
