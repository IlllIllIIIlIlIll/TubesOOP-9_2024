package com.mvz.zombies;

import com.mvz.Zombie;

public class Fanny extends Zombie {
    private boolean isGrappling = false;

    public Fanny(Integer x, Integer y) {
        super("Fanny", 150.0f, 10.0f, 1.0f, 5.0f, false, x, y);
    }

    public Fanny() {
        this(0, 0); // Parameter default untuk load
    }

    public void action() {
        if (isGrappling) {
            return;
        } else {
            isGrappling = true;
        }
        System.out.println("For victory!");
    }

    public void setGrappling(boolean grappling) {
        isGrappling = grappling;
    }
}
