package com.mvz.zombies;

import com.mvz.Zombie;

public class Polevaulting extends Zombie {

    private boolean isJumping = false;

    public Polevaulting(Integer x, Integer y) {
        super("Pole vaulting", 175.0f, 100.0f, 1.0f, 5.0f, false, x, y);
    }

    public Polevaulting() {
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
        System.out.println("Dikolongin tongkat awoawkwk");
    }
}
