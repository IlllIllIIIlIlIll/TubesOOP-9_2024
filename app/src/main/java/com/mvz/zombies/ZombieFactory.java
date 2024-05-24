package com.mvz.zombies;

import com.mvz.Zombie;
import com.mvz.Tile;

// Interface for creating different types of zombie factories
public interface ZombieFactory {
    // Method to create a zombie of the specified type at the given tile position
    Zombie createZombie(String type, Tile tile);
    // Method to return the list of available zombie types
    String[] getTypes();
}