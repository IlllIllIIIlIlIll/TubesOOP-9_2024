package com.mvz.zombies;

import com.mvz.Zombie;
import com.mvz.Tile;

// Abstract factory design pattern
public class WaterZombieFactory implements ZombieFactory{
private static final String[] types = {"Duckytube" /* , "Dolphinrider"*/};

    @Override
    public Zombie createZombie (String type, Tile tile) {
        switch (type) {
            case "Duckytube":
                return new Duckytube(tile);
            // case "Dolphinrider":
            //     return new Dolphinrider(tile);
            // ... tambahkan water zombie lain jika ada
            default:
                throw new IllegalArgumentException("Invalid type: " + type);
        }
    }

    @Override
    public String[] getTypes() {
        return types;
    }
}
