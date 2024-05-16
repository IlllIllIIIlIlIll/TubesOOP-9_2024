package com.mvz.serialization;

import com.google.gson.InstanceCreator;
import com.mvz.Plant;
import com.mvz.plants.LandPlantFactory;
import com.mvz.plants.WaterPlantFactory;
import com.mvz.Tile;

import java.lang.reflect.Type;

public class PlantInstanceCreator implements InstanceCreator<Plant> {
    private LandPlantFactory landFactory = new LandPlantFactory();
    private WaterPlantFactory waterFactory = new WaterPlantFactory();

    @Override
    public Plant createInstance(Type type) {
        return createPlantInstance("Peashooter", new Tile(0, 0, false));
    }

    public Plant createPlantInstance(String plantType, Tile tile) {
        if (tile.getIsA()) {
            return waterFactory.createPlant(plantType, tile);
        } else {
            return landFactory.createPlant(plantType, tile);
        }
    }
}
