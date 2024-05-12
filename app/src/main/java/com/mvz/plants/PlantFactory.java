package com.mvz.plants;

import com.mvz.Plant;
import com.mvz.Tile;

// untuk tiap jenis plant factory
public interface PlantFactory {
    Plant createPlant(String type, Tile tile);
    String[] getTypes();
}