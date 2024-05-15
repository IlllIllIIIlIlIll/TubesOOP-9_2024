package com.mvz.zombies;

import com.mvz.Zombie;
import com.mvz.Tile;

// Abstract factory design pattern
public class LandZombieFactory implements ZombieFactory {
private static final String[] types = {"Normal", "Conehead" /*, "Buckethead", "Football", "Jackinthebox", "Newspaper", "Polevaulting", "Screendoor"*/};
    
    @Override
    public Zombie createZombie(String type, Tile tile) {
        int x = 10;
        int y = tile.getY();
        switch (type) {
            case "Normal":
                return new Normal(x, y);
            case "Conehead":
                return new Conehead(x, y);
            // ... tambahkan land zombie lain jika ada
            default:
                throw new IllegalArgumentException("Invalid type: " + type);
        }
    }

    @Override
    public String[] getTypes() {
        return types;
    }
}
