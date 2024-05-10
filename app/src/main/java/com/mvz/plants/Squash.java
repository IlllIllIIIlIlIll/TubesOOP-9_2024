package com.mvz.plants;

import com.mvz.Plant;
import com.mvz.Tile;

public class Squash extends Plant {
    public Squash(Tile tile) {
        super("Squash", 50, 9999.0f,  5000.0f, 0.0f, 1, 10, false, tile);
    }

    public Squash() {
        super("Squash", 50, 9999.0f,  5000.0f, 0.0f, 1, 10, false);
    }

    public void attack(){

    }
    
    // baca tile +1 didepan dan belakangnya juga, prioritaskan yang di belakangnya, attack zommbie pada tile tsb
    public void action(){

    }
}
