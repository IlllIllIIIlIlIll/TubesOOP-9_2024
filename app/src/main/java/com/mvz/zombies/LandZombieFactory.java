package com.mvz.zombies;

import com.mvz.Zombie;
import com.mvz.Tile;

// Abstract factory design pattern
// Factory for creating land zombies
public class LandZombieFactory implements ZombieFactory {
// Array containing types of land zombies
private static final String[] types = {"Normal", "Conehead", "Buckethead", "Football", "Ra", "Jackinthebox", "Polevaulting", "Koran"};
    
    @Override
    // Method to create a specific type of land zombie
    public Zombie createZombie(String type, Tile tile) {
        // Set the initial x-coordinate for all land zombies to 10 (arbitrary choice)
        int x = 10;
        // Get the y-coordinate from the provided tile object
        int y = tile.getY();
        // Create a specific type of land zombie based on the given type
        switch (type) {
            case "Normal":
                return new Normal(x, y);
            case "Conehead":
                return new Conehead(x, y);
            case "Buckethead":
                return new Buckethead(x, y);
            case "Football":
                return new Football(x, y);
            case "Jackinthebox":
                return new Jackinthebox(x, y);
            case "Ra":
                return new Ra(x, y);
            case "Polevaulting":
                return new Polevaulting(x, y);
            case "Koran":
                return new Koran(x, y);
            // Add more land zombie types here if needed
            default:
                throw new IllegalArgumentException("Invalid type: " + type);
        }
    }

    @Override
    // Method to retrieve the array of available land zombie types
    public String[] getTypes() {
        return types;
    }
}
