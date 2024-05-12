package com.mvz.plants;

import com.mvz.Plant;
import com.mvz.Tile;
import com.mvz.Zombie;
import com.mvz.Character;

public class Peashooter extends Plant {
    public Peashooter(Tile tile) {
        super("Peashooter", 100, 100.0f,  25.0f, 4.0f, -1, 10, false, tile);
    }

    public Peashooter() {
        super("Peashooter", 100, 100.0f,  25.0f, 4.0f, -1, 10, false);
    }
    
    // how to attack zombie
    public void action(){
        

    }
}
