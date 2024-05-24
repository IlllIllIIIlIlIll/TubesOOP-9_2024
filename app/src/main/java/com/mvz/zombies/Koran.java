package com.mvz.zombies;

import com.mvz.Zombie;

// Class representing a Koran zombie, which extends the Zombie class
public class Koran extends Zombie {
    // Constructor with position parameters
    public Koran(Integer x, Integer y) {
        // Calls the superclass constructor with specific values for Koran
        super("Koran", 150.0f, 10.0f, 5.0f, 10.0f, false, x, y);
    }

    // Default constructor that sets the position to (0, 0)
    public Koran() {
        this(0, 0); // Default parameters for loading
    }

    // Method to define actions specific to Koran
    public void action() {
        // If the health of the Koran zombie drops below or equal to 75.0f
        if (health <= 75.0f) {
            // Increase the attack speed to 3.0f
            attack_speed = 3.0f;
        }
    }

}
