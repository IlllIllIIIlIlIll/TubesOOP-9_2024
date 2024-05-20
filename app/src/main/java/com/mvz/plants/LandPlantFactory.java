package com.mvz.plants;

import com.mvz.Plant;
import com.mvz.Tile;

// Abstract factory design pattern
public class LandPlantFactory implements PlantFactory {
private static final String[] types = {"Cherrybomb", "Jalapeno", "Peashooter", "Repeater", "Snowpea", "Squash", "Sunflower", "Wallnut"} ;
    
    @Override
    public Plant createPlant(String type, Tile tile) {
        int x = tile.getX();
        int y = tile.getY();
        switch (type) {
            case "Cherrybomb":
                return new Cherrybomb(x, y);
            case "Jalapeno":
                return new Jalapeno(x, y);
            case "Peashooter" :
                return new Peashooter(x, y);
            case "Repeater":
                return new Repeater(x, y);
            case "Snow pea" :
                return new Snowpea(x, y);
            case "Squash" :
                return new Squash(x, y);
            case "Sunflower" :
                return new Sunflower(x, y);
            case "Wall nut" :
                return new Wallnut(x, y);
            default:
                throw new IllegalArgumentException("Invalid type: " + type);
        }
    }

    @Override
    public String[] getTypes() {
        return types;
    }
}
