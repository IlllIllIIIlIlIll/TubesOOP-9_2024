package com.mvz.plants;

import com.mvz.Plant;
import com.mvz.Tile;

// Implements the abstract factory design pattern for creating different types of land plants
public class LandPlantFactory implements PlantFactory {
// Array listing the types of plants that can be created
private static final String[] types = {"Cherrybomb", "Jalapeno", "Peashooter", "Repeater", "Snowpea", "Squash", "Sunflower", "Wallnut"} ;
    
    @Override
    // Factory method to create a plant of the specified type at a given tile location
    public Plant createPlant(String type, Tile tile) {
        int x = tile.getX();
        int y = tile.getY();
        // Use a switch statement to determine which plant to instantiate based on the type string
        switch (type) {
            case "Cherrybomb":
                return new Cherrybomb(x, y);
            case "Jalapeno":
                return new Jalapeno(x, y);
            case "Peashooter" :
                return new Peashooter(x, y);
            case "Repeater":
                return new Repeater(x, y);
            case "Snowpea" :
                return new Snowpea(x, y);
            case "Squash" :
                return new Squash(x, y);
            case "Sunflower" :
                return new Sunflower(x, y);
            case "Wallnut" :
                return new Wallnut(x, y);
            // Throw an exception if an invalid type is requested
            default:
                throw new IllegalArgumentException("Invalid type: " + type);
        }
    }

    @Override
    // Method to retrieve the array of plant types supported by this factory
    public String[] getTypes() {
        return types;
    }
}
