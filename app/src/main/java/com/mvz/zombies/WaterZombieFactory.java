package com.mvz.zombies;

import com.mvz.Zombie;
import com.mvz.Tile;

// Implements the Abstract Factory design pattern for creating water zombies
public class WaterZombieFactory implements ZombieFactory{
private static final String[] types = {"Duckytube", "Dolphinrider"};

    @Override
    // Creates a zombie of the specified type at the given tile position
    public Zombie createZombie (String type, Tile tile) {
        int x = 10;
        int y = tile.getY();
        switch (type) {
            case "Duckytube":
                return new Duckytube(x, y);
            case "Dolphinrider":
                return new Dolphinrider(x, y);
            // Add other water zombies if needed
            default:
                throw new IllegalArgumentException("Invalid type: " + type);
        }
    }

    @Override
    // Returns the list of available zombie types
    public String[] getTypes() {
        return types;
    }
}
