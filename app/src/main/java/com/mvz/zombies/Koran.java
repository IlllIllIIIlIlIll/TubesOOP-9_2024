package com.mvz.zombies;

import com.mvz.Zombie;

public class Koran extends Zombie {

    public Koran(Integer x, Integer y) {
        super("Koran Zombie", 150.0f, 10.0f, 5.0f, 10.0f, false, x, y);
    }

    public Koran() {
        super("Koran Zombie", 150.0f, 10.0f, 5.0f, 10.0f, false); // Parameter default untuk load
    }

    public void action() {
        if (health <= 75.0f) {
            attack_speed = 3.0f;
        }
    }

}
