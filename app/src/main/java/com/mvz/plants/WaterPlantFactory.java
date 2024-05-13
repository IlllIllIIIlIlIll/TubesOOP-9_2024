package com.mvz.plants;

import com.mvz.Plant;
import com.mvz.Tile;

// Abstract factory design pattern
public class WaterPlantFactory implements PlantFactory {
private static final String[] types = {"Lilypad", "Tanglekelp"};
    
    @Override
    public Plant createPlant(String type, Tile tile) {
        switch (type) {
            case "Lilypad":
                return new Lilypad(tile);
            case "Tanglekelp":
                return new Tanglekelp(tile);
            default:
                throw new IllegalArgumentException("Invalid type: " + type);
        }
    }

    @Override
    public String[] getTypes() {
        return types;
    }
}
