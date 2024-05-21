package com.mvz.zombies;

import com.mvz.Zombie;

public class Duckytube extends Zombie {
    public Duckytube(Integer x, Integer y) {
        super("Ducky Tube Zombie", 100.0f, 100.0f, 1.0f, 10.0f, true, x, y);
    }

    public Duckytube() {
        super("Ducky Tube Zombie", 100.0f, 100.0f, 1.0f, 10.0f, true); // Parameter default untuk load
    }

    public void action(){

    }
}
