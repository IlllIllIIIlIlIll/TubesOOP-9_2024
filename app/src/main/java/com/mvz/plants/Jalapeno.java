package com.mvz.plants;

import com.mvz.Plant;

public class Jalapeno extends Plant {
    public Jalapeno(Integer x, Integer y) {
        super("Jalapeno", 125, 9999.0f,  1800.0f, 0.0f, -1, 35, false, x, y);
    }

    public Jalapeno() {
        super("Jalapeno", 125, 9999.0f,  1800.0f, 0.0f, -1, 35, false);
    }

    public void action(){

    }
}
