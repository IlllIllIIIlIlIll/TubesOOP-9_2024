package com.mvz.zombies;

import com.mvz.Zombie;
import com.mvz.Tile;

// untuk tiap jenis zombie factory
public interface ZombieFactory {
    Zombie createZombie(String type, Tile tile);
    String[] getTypes();
}