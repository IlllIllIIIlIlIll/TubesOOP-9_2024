package com.mvz.zombies;


import com.mvz.Zombie;

// Class representing a Normal zombie, which extends the Zombie class
public class Normal extends Zombie {
    // Initializes a Normal zombie with specific attributes and position.
    public Normal(Integer x, Integer y) {
        super("Normal zombie", 125.0f, 100.0f, 1.0f, 10.0f, false, x, y);
    }

    // Default constructor initializes position at (0, 0)
    public Normal() {
        this(0, 0); // Default parameters for loading
    }

    // Defines the action behavior of the Normal zombie
    public void action(){
        
    }
}
