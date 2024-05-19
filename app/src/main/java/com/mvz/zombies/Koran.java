package com.mvz.zombies;

import com.mvz.Zombie;

public class Koran extends Zombie {

    public Koran(Integer x, Integer y) {
        super("Koran", 150.0f, 10.0f, 5.0f, 10.0f, false, x, y);
    }

    public Koran() {
        this(0, 0); // Parameter default untuk load
    }

    public void action() {
        if (health <= 75.0f) {
            attack_speed = 3.0f;
        }
    }

}
