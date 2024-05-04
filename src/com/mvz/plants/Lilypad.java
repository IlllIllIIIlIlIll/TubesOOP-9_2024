package com.mvz.plants;

import com.mvz.Plant;
import com.mvz.Tile;

public class Lilypad extends Plant {
    public Lilypad(Tile tile) {
        super("Lilypad", 25, 100.0f,  0.0f, 0.0f, 0, 10, true, tile);
    }

    // tanaman bisa ditaruh di isAquatic tile jika terdapat lilypad disana    
    public void action(){

    }
}
