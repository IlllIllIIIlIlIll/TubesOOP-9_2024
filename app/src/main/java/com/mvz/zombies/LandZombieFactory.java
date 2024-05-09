package com.mvz.zombies;

import com.mvz.Zombie;
import com.mvz.Tile;

// Abstract factory design pattern
public class LandZombieFactory implements ZombieFactory {
private static final String[] types = {"Normal", "Conehead" /*, "Buckethead", "Football", "Jackinthebox", "Newspaper", "Polevaulting", "Screendoor"*/};
    
    @Override
    public Zombie createZombie(String type, Tile tile) {
        switch (type) {
            case "Normal":
                return new Normal(tile);
            case "Conehead":
                return new Conehead(tile);
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
