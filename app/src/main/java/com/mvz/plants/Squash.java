package com.mvz.plants;

import com.mvz.Plant;

public class Squash extends Plant {
    public Squash(Integer x, Integer y) {
        super("Squash", 50, 9999.0f,  5000.0f, 0.0f, 1, 10, false, x, y);
    }

    public Squash() {
        super("Squash", 50, 9999.0f,  5000.0f, 0.0f, 1, 10, false);
    }
    
    public void action(){

    }
}