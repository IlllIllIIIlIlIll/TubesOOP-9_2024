package com.mvz.zombies;

import com.mvz.Zombie;
import com.mvz.Tile;

// Abstract factory design pattern
public class WaterZombieFactory implements ZombieFactory{
private static final String[] types = {"Duckytube", "Dolphinrider"};
public static final String ANSI_RESET = "\u001B[0m";
public static final String ANSI_RED = "\u001B[31m";

    @Override
    public Zombie createZombie (String type, Tile tile) {
        int x = 10;
        int y = tile.getY();
        switch (type) {
            case "Duckytube":
                return new Duckytube(x, y);
            case "Dolphinrider":
                return new Dolphinrider(x, y);
            // ... tambahkan water zombie lain jika ada
            default:
                throw new IllegalArgumentException(ANSI_RED + "Invalid type: " + type + ANSI_RESET);
        }
    }

    @Override
    public String[] getTypes() {
        return types;
    }
}
