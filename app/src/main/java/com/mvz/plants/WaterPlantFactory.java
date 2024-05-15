package com.mvz.plants;

import com.mvz.Plant;
import com.mvz.Tile;

// Abstract factory design pattern
public class WaterPlantFactory implements PlantFactory {
private static final String[] types = {"Lilypad", "Tanglekelp"};
    
    @Override
    public Plant createPlant(String type, Tile tile) {
        int x = tile.getX();
        int y = tile.getY();
        switch (type) {
            case "Lilypad":
                return new Lilypad(x, y);
            case "Tanglekelp":
                return new Tanglekelp(x, y);
            default:
                throw new IllegalArgumentException("Invalid type: " + type);
        }
    }

    @Override
    public String[] getTypes() {
        return types;
    }
}
