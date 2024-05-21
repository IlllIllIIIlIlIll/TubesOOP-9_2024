package com.mvz.zombies;

import com.mvz.Zombie;

public class Football extends Zombie {
    public Football(Integer x, Integer y) {
        super("Football Zombie", 150.0f, 100.0f, 1.0f, 10.0f, false, x, y);
    }

    public Football() {
        super("Football Zombie", 150.0f, 100.0f, 1.0f, 10.0f, false); // Parameter default untuk load
    }

    public void action(){
        if (health <= 170.0f) {
            attack_damage = 200.0f;
        }
    }
}
