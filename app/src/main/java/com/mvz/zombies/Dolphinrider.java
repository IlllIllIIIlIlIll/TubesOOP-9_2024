package com.mvz.zombies;

import com.mvz.Zombie;

// Class representing a Dolphinrider zombie, which extends the Zombie class
public class Dolphinrider extends Zombie {

    // Field to track if the Dolphinrider is jumping
    private boolean isJumping = false;

    // Constructor with position parameters
    public Dolphinrider(Integer x, Integer y) {
        // Calls the superclass constructor with specific values for Dolphinrider
        super("Dolphin rider", 175.0f, 100.0f, 1.0f, 10.0f, true, x, y);
    }

    // Default constructor that sets the position to (0, 0)
    public Dolphinrider() {
        this(0, 0); // Default parameters for loading
    }

    // Method to check if the Dolphinrider is jumping
    public boolean isJumping(){
        return isJumping;
    }

    // Method to define actions specific to Dolphinrider
    public void action(){
        // If already jumping, do nothing
        if (isJumping) {
            return;
        } else {
            // Set jumping state to true and perform the jump action
            isJumping = true;
        }
        // Print a message indicating the jump action
        System.out.println("Dikolongin lumba lumba awoawkwk");
    }
}
