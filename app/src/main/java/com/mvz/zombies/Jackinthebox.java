package com.mvz.zombies;

import com.mvz.Zombie;

public class Jackinthebox extends Zombie {
    public Jackinthebox(Integer x, Integer y) {
        super("Jack in the box", 150.0f, 1500.0f, 1.0f, 5.0f, false, x, y);
    }

    public void action(){
        //decrease health until 0
        decreaseHealth(health);
    }
}
