package com.mvz.plants;

import com.mvz.Plant;

public class Lilypad extends Plant {
    public Lilypad(Integer x, Integer y) {
        super("Lilypad", 25, 100.0f,  0.0f, 0.0f, 0, 10, true, x, y);
    }

    public Lilypad() {
        super("Lilypad", 25, 100.0f,  0.0f, 0.0f, 0, 10, true);
    }
    
    // tanaman bisa ditaruh di isAquatic tile jika terdapat lilypad disana    
    public void action(){

    }
    public void test(){
        
    }
}
