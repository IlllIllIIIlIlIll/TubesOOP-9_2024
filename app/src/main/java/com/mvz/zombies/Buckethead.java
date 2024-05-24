package com.mvz.zombies;

import com.mvz.Zombie;

// Class representing a Buckethead zombie, which extends the Zombie class
public class Buckethead extends Zombie {
    // Constructor with position parameters
    public Buckethead(Integer x, Integer y) {
        // Calls the superclass constructor with specific values for Buckethead
        super("Bucket head", 300.0f, 100.0f, 1.0f, 10.0f, false, x, y);
    }

    // Default constructor that sets the position to (0, 0)
    public Buckethead() {
        this(0, 0); // Default parameters for loading
    }

    // Method to define actions specific to Buckethead
    public void action(){

    }
}
