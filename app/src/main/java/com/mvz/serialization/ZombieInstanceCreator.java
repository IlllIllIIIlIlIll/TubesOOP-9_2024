package com.mvz.serialization;

import com.google.gson.InstanceCreator;
import com.mvz.Zombie;
import com.mvz.zombies.LandZombieFactory;
import com.mvz.zombies.WaterZombieFactory;
import com.mvz.Tile;

import java.lang.reflect.Type;

public class ZombieInstanceCreator implements InstanceCreator<Zombie> {
    private LandZombieFactory landFactory = new LandZombieFactory();
    private WaterZombieFactory waterFactory = new WaterZombieFactory();

    @Override
    public Zombie createInstance(Type type) {
        // Use a default type and tile for creating an instance
        return createZombieInstance("Normal", new Tile(0, 0, false));
    }

    public Zombie createZombieInstance(String zombieType, Tile tile) {
        if (tile.getIsA()) {
            return waterFactory.createZombie(zombieType, tile);
        } else {
            return landFactory.createZombie(zombieType, tile);
        }
    }
}
