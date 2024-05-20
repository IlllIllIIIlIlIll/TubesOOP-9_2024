package com.mvz.zombies;

import com.mvz.Zombie;

public class Conehead extends Zombie {
    public Conehead(Integer x, Integer y) {
        super("Conehead zombie", 250.0f, 100.0f, 1.0f, 10.0f, false, x, y);
    }

    public Conehead() {
        this(0, 0); // Parameter default untuk load
    }

    public void action(){
  
    }
}
