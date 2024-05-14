package com.mvz.zombies;

import com.mvz.Zombie;

public class Conehead extends Zombie {
    public Conehead(Integer x, Integer y) {
        super("Conehead zombie", 250.0f, 100.0f, 1.0f, 5.0f, false, x, y);
    }

    // how to attack plant within x - 1
    public void action(){

    }
}
