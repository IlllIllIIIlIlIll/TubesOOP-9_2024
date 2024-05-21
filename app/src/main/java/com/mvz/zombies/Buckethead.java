package com.mvz.zombies;

import com.mvz.Zombie;

public class Buckethead extends Zombie {

    public Buckethead(Integer x, Integer y) {
        super("Buckethead Zombie", 300.0f, 100.0f, 1.0f, 10.0f, false, x, y);
    }

    public Buckethead() {
        super("Buckethead Zombie", 300.0f, 100.0f, 1.0f, 10.0f, false); // Parameter default untuk load
    }

    public void action(){

    }
}
