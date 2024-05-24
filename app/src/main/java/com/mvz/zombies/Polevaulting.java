package com.mvz.zombies;

import com.mvz.Zombie;

// Class representing a Polevaulting zombie, which extends the Zombie class
public class Polevaulting extends Zombie {
    // Tracks if the zombie is currently jumping
    private boolean isJumping = false;
    // Initializes a Polevaulting zombie with specific attributes and position
    public Polevaulting(Integer x, Integer y) {
        super("Pole vaulting", 175.0f, 100.0f, 1.0f, 10.0f, false, x, y);
    }
    // Default constructor initializes position at (0, 0)
    public Polevaulting() {
        this(0, 0); // Default position for loading
    }
    // Returns the jumping state of the zombie
    public boolean isJumping(){
        return isJumping;
    }
    // Defines the action behavior of the Polevaulting zombie
    public void action(){
        if (isJumping) {
            return; // If already jumping, do nothing
        } else {
            isJumping = true;   // Set to jumping state
        }
        System.out.println("Dikolongin tongkat awoawkwk");
    }
}
