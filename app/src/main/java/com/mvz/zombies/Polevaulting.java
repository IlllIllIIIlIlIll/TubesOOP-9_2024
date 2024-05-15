package com.mvz.zombies;

import com.mvz.Zombie;

public class Polevaulting extends Zombie {

    private boolean isJumping = false;

    public Polevaulting(Integer x, Integer y) {
        super("Pole vaulting", 175.0f, 100.0f, 1.0f, 5.0f, false, x, y);
    }

    public void action(){
        // kalau udah lompat
        int newX = x - 1;
        if (isJumping) {
            return;
        }
        else {
            x = newX;
            isJumping = true;
        }
    }
}
