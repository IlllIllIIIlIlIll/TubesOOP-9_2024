package com.mvz.serialization;

import com.google.gson.InstanceCreator;
import com.mvz.Zombie;
import com.mvz.zombies.LandZombieFactory;
import com.mvz.zombies.WaterZombieFactory;
import com.mvz.Tile;

import java.lang.reflect.Type;

// Custom instance creator for Zombie objects, used with Gson serialization
public class ZombieInstanceCreator implements InstanceCreator<Zombie> {
    private LandZombieFactory landFactory = new LandZombieFactory();
    private WaterZombieFactory waterFactory = new WaterZombieFactory();

    @Override
    // Override the createInstance method from InstanceCreator interface
    public Zombie createInstance(Type type) {
        // Default instance creation with a sample zombie type and tile
        return createZombieInstance("Normal", new Tile(0, 0, false));
    }

    // Method to create a Zombie instance based on zombie type and tile properties
    public Zombie createZombieInstance(String zombieType, Tile tile) {
        // Check if the tile is for a water zombie
        if (tile.getIsA()) {
            // Create and return a water zombie
            return waterFactory.createZombie(zombieType, tile);
        } else {
            // Create and return a land zombie
            return landFactory.createZombie(zombieType, tile);
        }
    }
}
