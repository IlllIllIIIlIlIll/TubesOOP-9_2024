package com.mvz.zombies;

import com.mvz.Zombie;

// Class representing a Conehead zombie, which extends the Zombie class
public class Conehead extends Zombie {
    // Constructor with position parameters
    public Conehead(Integer x, Integer y) {
        // Calls the superclass constructor with specific values for Conehead
        super("Conehead zombie", 250.0f, 100.0f, 1.0f, 10.0f, false, x, y);
    }

    // Default constructor that sets the position to (0, 0)
    public Conehead() {
        this(0, 0); // Default parameters for loading
    }

    // Method to define actions specific to Conehead
    public void action(){
  
    }
}
