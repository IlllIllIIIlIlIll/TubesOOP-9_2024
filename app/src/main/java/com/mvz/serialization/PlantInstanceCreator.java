package com.mvz.serialization;

import com.google.gson.InstanceCreator;
import com.mvz.Plant;
import com.mvz.plants.LandPlantFactory;
import com.mvz.plants.WaterPlantFactory;
import com.mvz.Tile;

import java.lang.reflect.Type;

// Custom instance creator for Plant objects, used with Gson serialization
public class PlantInstanceCreator implements InstanceCreator<Plant> {
    private LandPlantFactory landFactory = new LandPlantFactory();
    private WaterPlantFactory waterFactory = new WaterPlantFactory();

    @Override
    // Override the createInstance method from InstanceCreator interface
    public Plant createInstance(Type type) {
        // Default instance creation with a sample plant type and tile
        return createPlantInstance("Peashooter", new Tile(0, 0, false));
    }

    // Method to create a Plant instance based on plant type and tile properties
    public Plant createPlantInstance(String plantType, Tile tile) {
        // Check if the tile is for a water plant
        if (tile.getIsA()) {
            // Create and return a water plant
            return waterFactory.createPlant(plantType, tile);
        } else {
            // Create and return a land plant
            return landFactory.createPlant(plantType, tile);
        }
    }
}
