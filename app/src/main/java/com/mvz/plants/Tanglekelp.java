package com.mvz.plants;

import com.mvz.Plant;

public class Tanglekelp extends Plant {
    public Tanglekelp(Integer x, Integer y) {
        super("Tangle kelp", 80, 100.0f,  5000.0f, 0.0f, 1, 20, true, x, y);
    }

    public Tanglekelp() {
        super("Tangle kelp", 80, 100.0f,  5000.0f, 0.0f, 1, 20, true);
    }

    public void action(){
        decreaseHealth(health);
        System.out.println("Selain menarik perhatian, aku juga menarik zombie");
    }
}
