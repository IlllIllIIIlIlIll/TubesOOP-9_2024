package com.mvz.menu;

import com.mvz.Inventory;

public class ZombieListCommand implements Command {
    @Override
    public void execute() {
        // better user experience
        System.out.print("\033[H\033[2J");
        System.out.flush();
        
        Inventory inventory = new Inventory();
        inventory.printZombie();
    }
}