package com.mvz.zombies;

import com.mvz.Zombie;

// Class representing a Jackinthebox zombie, which extends the Zombie class
public class Jackinthebox extends Zombie {
    // Constructor with position parameters
    public Jackinthebox(Integer x, Integer y) {
        // Calls the superclass constructor with specific values for Jackinthebox
        super("Jack in the box", 150.0f, 9999.0f, 1.0f, 10.0f, false, x, y);
        // Jackinthebox has an incredibly high attack power (9999.0f), making it deadly
    }

    // Default constructor that sets the position to (0, 0)
    public Jackinthebox() {
        this(0, 0); // Default parameters for loading
    }

    // Method to define actions specific to Jackinthebox
    public void action(){
        // Decrease health until it reaches 0, essentially killing the Jackinthebox
        decreaseHealth(health);
        System.out.println("aku mati"); // Prints a message indicating its death
    }
}
