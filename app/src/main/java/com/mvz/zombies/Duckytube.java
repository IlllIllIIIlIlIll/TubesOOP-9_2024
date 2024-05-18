package com.mvz.zombies;

import com.mvz.Zombie;

public class Duckytube extends Zombie {
    public Duckytube(Integer x, Integer y) {
        super("Ducky tube", 100.0f, 100.0f, 1.0f, 5.0f, true, x, y);
    }

    public Duckytube() {
        this(0, 0); // Parameter default untuk load
    }

    public void action(){

    }
}
