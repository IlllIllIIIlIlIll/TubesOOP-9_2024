package com.mvz.plants;

import com.mvz.Plant;

public class Wallnut extends Plant {
    public Wallnut(Integer x, Integer y) {
        super("Wall nut", 50, 1000.0f,  0.0f, 0.0f, 0, 20, false, x, y);
    }

    public Wallnut() {
        super("Wall nut", 50, 1000.0f,  0.0f, 0.0f, 0, 20, false);
    }
    
    // do nothing
    public void action(){

    }
}
