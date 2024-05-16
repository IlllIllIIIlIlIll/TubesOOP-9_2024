package com.mvz.plants;

import com.mvz.Plant;

public class Peashooter extends Plant {

    public Peashooter(Integer x, Integer y) {
        super("Peashooter", 100, 100.0f,  25.0f, 4.0f, -1, 10, false, x, y);
    }

    public Peashooter() {
        super("Peashooter", 100, 100.0f,  25.0f, 4.0f, -1, 10, false);
    }
    
    // how to attack zombie
    public void action(){

        }
}
