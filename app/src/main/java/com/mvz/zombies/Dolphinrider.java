package com.mvz.zombies;

import com.mvz.Zombie;

public class Dolphinrider extends Zombie {

    private boolean isJumping = false;

    public Dolphinrider(Integer x, Integer y) {
        super("Dolphin rider", 175.0f, 100.0f, 1.0f, 5.0f, true, x, y);
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
