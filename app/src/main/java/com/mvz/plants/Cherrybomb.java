package com.mvz.plants;

import com.mvz.Plant;

public class Cherrybomb extends Plant {
    public Cherrybomb(Integer x, Integer y) {
        super("Cherry bomb", 150, 9999.0f,  1800.0f, 0.0f, 3, 30, false, x, y);
    }

    public Cherrybomb() {
        super("Cherry bomb", 150, 9999.0f,  1800.0f, 0.0f, 3, 30, false);
    }

    public void action(){
        decreaseHealth(health);
        System.out.println("Bang aku udah meledak karena kegendutan");
    }
}
