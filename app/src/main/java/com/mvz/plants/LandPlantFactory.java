package com.mvz.plants;

import com.mvz.Plant;
import com.mvz.Tile;

// Abstract factory design pattern
public class LandPlantFactory implements PlantFactory {
private static final String[] types = {"Cherrybomb", "Jalapeno", "Peashooter", "Repeater", "Snowpea", "Squash", "Sunflower", "Wallnut"} ;
    
    @Override
    public Plant createPlant(String type, Tile tile) {
        switch (type) {
            case "Cherrybomb":
                return new Cherrybomb(tile);
            case "Jalapeno":
                return new Jalapeno(tile);
            case "Peashooter" :
                return new Peashooter(tile);
            case "Repeater":
                return new Repeater(tile);
            case "Snowpea" :
                return new Snowpea(tile);
            case "Squash" :
                return new Squash(tile);
            case "Sunflower" :
                return new Sunflower(tile);
            case "Wallnut" :
                return new Wallnut(tile);
            default:
                throw new IllegalArgumentException("Invalid type: " + type);
        }
    }

    @Override
    public String[] getTypes() {
        return types;
    }
}
