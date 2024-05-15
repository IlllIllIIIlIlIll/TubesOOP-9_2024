package com.mvz.zombies;

import com.mvz.Zombie;
import com.mvz.Tile;

// Abstract factory design pattern
public class WaterZombieFactory implements ZombieFactory{
private static final String[] types = {"Duckytube" /* , "Dolphinrider"*/};

    @Override
    public Zombie createZombie (String type, Tile tile) {
        int x = 10;
        int y = tile.getY();
        switch (type) {
            case "Duckytube":
                return new Duckytube(x, y);
            // case "Dolphinrider":
            //     return new Dolphinrider(x, y);
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
