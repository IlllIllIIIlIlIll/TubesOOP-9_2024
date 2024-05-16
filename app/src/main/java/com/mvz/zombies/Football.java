package com.mvz.zombies;

import com.mvz.Zombie;

public class Football extends Zombie {
    public Football(Integer x, Integer y) {
        super("Football", 350.0f, 100.0f, 1.0f, 5.0f, false, x, y);
    }

    public Football() {
        this(0, 0); // Parameter default untuk load
    }

    public void action(){
        if (health <= 170.0f) {
            attack_damage = 150.0f;
        }
    }
}
