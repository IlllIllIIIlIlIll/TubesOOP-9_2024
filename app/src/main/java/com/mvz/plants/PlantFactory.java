package com.mvz.plants;

import com.mvz.Plant;
import com.mvz.Tile;

// Interface defining methods for creating plants of various types using a factory pattern
public interface PlantFactory {
    // Creates a plant of the specified type at a given tile location
    Plant createPlant(String type, Tile tile);
    // Retrieves an array of plant types supported by this factory
    String[] getTypes();
}