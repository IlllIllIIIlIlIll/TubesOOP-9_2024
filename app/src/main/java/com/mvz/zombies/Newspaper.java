package com.mvz.zombies;

import com.mvz.Zombie;

public class Newspaper extends Zombie {

    public Newspaper(Integer x, Integer y) {
        super("Newspaper", 150.0f, 100.0f, 1.0f, 5.0f, false, x, y);
    }

    public Newspaper() {
        this(0, 0); // Parameter default untuk load
    }

    public void action(){
        if (health <= 75.0f) {
            attack_damage = 150.0f;
        }
    }
}
