package com.mvz.menu;

import com.mvz.Inventory;

public class ZombieListCommand implements Command {
    @Override
    public void execute() {
        Inventory.getInstance().printZombie();
    }
}