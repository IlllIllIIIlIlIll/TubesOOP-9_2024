package com.mvz.zombies;

import com.mvz.Zombie;

public class Jackinthebox extends Zombie {
    public Jackinthebox(Integer x, Integer y) {
        super("Jack In The Box Zombie", 150.0f, 9999.0f, 1.0f, 10.0f, false, x, y);
    }

    public Jackinthebox() {
        super("Jack In The Box Zombie", 150.0f, 9999.0f, 1.0f, 10.0f, false); // Parameter default untuk load
    }

    public void action(){
        //decrease health until 0
        decreaseHealth(health);
    }
}
