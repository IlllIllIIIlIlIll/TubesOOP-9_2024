package com.mvz.zombies;

import com.mvz.Zombie;

// Class representing a Duckytube zombie, which extends the Zombie class
public class Duckytube extends Zombie {
    // Constructor with position parameters
    public Duckytube(Integer x, Integer y) {
        // Calls the superclass constructor with specific values for Duckytube
        super("Ducky tube", 100.0f, 100.0f, 1.0f, 10.0f, true, x, y);
    }

    // Default constructor that sets the position to (0, 0)
    public Duckytube() {
        this(0, 0); // Default parameters for loading
    }

    // Method to define actions specific to Duckytube
    public void action(){

    }
}
