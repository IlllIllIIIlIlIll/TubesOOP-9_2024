package com.mvz.plants;

import com.mvz.Plant;
import com.mvz.Tile;

// Abstract factory design pattern
public class WaterPlantFactory implements PlantFactory {
private static final String[] types = {"Lilypad", "Tanglekelp"};

public static final String ANSI_RESET = "\u001B[0m";
public static final String ANSI_RED = "\u001B[31m";
    
    @Override
    public Plant createPlant(String type, Tile tile) {
        int x = tile.getX();
        int y = tile.getY();
        switch (type) {
            case "Lilypad":
                return new Lilypad(x, y);
            case "Tangle kelp":
                return new Tanglekelp(x, y);
            default:
                throw new IllegalArgumentException(ANSI_RED + "Invalid type: " + type + ANSI_RESET);
        }
    }

    @Override
    public String[] getTypes() {
        return types;
    }
}
