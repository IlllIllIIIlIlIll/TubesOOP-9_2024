package com.mvz;

public class Menu {
    private Inventory inventory;

    // constructor
    public Menu(Inventory inventory) {
        this.inventory = inventory;
    }

    public void start() {
        System.out.println("Game started!");
        // Add your game logic here
    }

    public void help() {
        System.out.println("This is the help menu. Here are some tips...");
        // Add your help information here
    }

    public void plantList() {
        System.out.println("Here is a list of plants:");

        inventory.printInventory();

    }

    public void zombieList() {
        System.out.println("Here is a list of zombies:");
        // Add your zombie list here
    }

}
