package com.mvz.zombies;

import com.mvz.Zombie;

public class Buckethead extends Zombie {

    public Buckethead(Integer x, Integer y) {
        super("Bucket head", 300.0f, 100.0f, 1.0f, 5.0f, false, x, y);
    }

    public Buckethead() {
        this(0, 0); // Parameter default untuk load
    }

    public void action(){

    }
}
