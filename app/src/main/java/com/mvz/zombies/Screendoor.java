package com.mvz.zombies;

import com.mvz.Zombie;

// Class representing a Screendoor zombie, which extends the Zombie class
public class Screendoor extends Zombie {

    // Initializes a Screendoor zombie with specific attributes and position
    public Screendoor(Integer x, Integer y) {
        super("Screen door", 300.0f, 100.0f, 1.0f, 5.0f, false, x, y);
    }
    
    // Defines the action behavior of the Screendoor zombie
    public void action(){

    }
}
