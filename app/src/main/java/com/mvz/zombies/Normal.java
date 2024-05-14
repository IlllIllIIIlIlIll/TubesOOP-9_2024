package com.mvz.zombies;

import com.mvz.Zombie;

public class Normal extends Zombie {
    public Normal(Integer x, Integer y) {
        super("Normal zombie", 125.0f, 100.0f, 1.0f, 5.0f, false, x, y);
    }

    // how to attack plant within x - 1
    public void action(){

    }
}
