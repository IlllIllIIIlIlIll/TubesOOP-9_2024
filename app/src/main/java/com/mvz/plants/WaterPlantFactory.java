package com.mvz.plants;

import com.mvz.Plant;
import com.mvz.Tile;

// Implements the abstract factory design pattern for creating water-related plants
public class WaterPlantFactory implements PlantFactory {
// Array holding the types of plants this factory can produce
private static final String[] types = {"Lilypad", "Tanglekelp"};
    
    @Override
    // Factory method to create a plant of the specified type at a given tile location
    public Plant createPlant(String type, Tile tile) {
        // Extracting x and y coordinates from the tile
        int x = tile.getX();
        int y = tile.getY();
        // Using a switch statement to determine which plant to create based on the type parameter
        switch (type) {
            case "Lilypad":
                // Creating a new Lilypad instance at the specified coordinates
                return new Lilypad(x, y);
            case "Tanglekelp":
                // Creating a new Tanglekelp instance at the specified coordinates
                return new Tanglekelp(x, y);
            default:
                // Throwing an exception if an invalid type is passed
                throw new IllegalArgumentException("Invalid type: " + type);
        }
    }

    @Override
    // Method to retrieve an array of plant types supported by this factory
    public String[] getTypes() {
        // Returning the array of supported plant types
        return types;
    }
}
