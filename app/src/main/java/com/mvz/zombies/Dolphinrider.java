package com.mvz.zombies;

import com.mvz.Zombie;

public class Dolphinrider extends Zombie {

    private boolean isJumping = false;

    public Dolphinrider(Integer x, Integer y) {
        super("Dolphin rider", 175.0f, 100.0f, 1.0f, 5.0f, true, x, y);
    }

    public Dolphinrider() {
        this(0, 0); // Parameter default untuk load
    }

    public boolean isJumping(){
        return isJumping;
    }

    public void action(){
        if (isJumping) {
            return;
        } else {
            isJumping = true;
        }
        System.out.println("Dikolongin lumba lumba awoawkwk");
    }
}
