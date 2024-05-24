package com.mvz.zombies;

import com.mvz.Zombie;

// Class representing a Football zombie, which extends the Zombie class
public class Football extends Zombie {
    // Constructor with position parameters
    public Football(Integer x, Integer y) {
        // Calls the superclass constructor with specific values for Football
        super("Football", 150.0f, 100.0f, 1.0f, 10.0f, false, x, y);
    }

    // Default constructor that sets the position to (0, 0)
    public Football() {
        this(0, 0); // Default parameters for loading
    }

    // Method to define actions specific to Football
    public void action(){
        // Check if the health of the Football zombie is below or equal to 170.0f
        if (health <= 170.0f) {
            // If condition is met, increase the attack damage to 200.0f
            attack_damage = 200.0f;
        }
    }
}
