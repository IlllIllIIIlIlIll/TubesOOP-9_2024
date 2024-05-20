package com.mvz.zombies;


import com.mvz.Zombie;

public class Normal extends Zombie {
    public Normal(Integer x, Integer y) {
        super("Normal zombie", 125.0f, 100.0f, 1.0f, 10.0f, false, x, y);
    }

    public Normal() {
        this(0, 0); // Parameter default untuk load
    }

    public void action(){
        
    }
}
