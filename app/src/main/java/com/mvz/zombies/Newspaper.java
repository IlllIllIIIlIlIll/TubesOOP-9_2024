package com.mvz.zombies;

import com.mvz.Zombie;

// The Newspaper class extends the Zombie class
public class Newspaper extends Zombie {

    // Constructor to initialize a Newspaper zombie with specific attributes
    public Newspaper(Integer x, Integer y) {
        // Calls the parent class (Zombie) constructor with specific parameters.
        super("Newspaper", 150.0f, 100.0f, 1.0f, 5.0f, false, x, y);
    }

    // Method to define the action behavior of the Newspaper zombie
    // If the health drops to 75.0 or below, the attack damage is increased to 150.0
    public void action(){
        if (health <= 75.0f) {
            attack_damage = 150.0f;
        }
    }
}
