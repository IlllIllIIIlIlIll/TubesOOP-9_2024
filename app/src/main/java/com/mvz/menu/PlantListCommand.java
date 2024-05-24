package com.mvz.menu;
import com.mvz.*;

public class PlantListCommand implements Command {
    @Override
    // Printing the list of plants from the inventory
    public void execute() {
        System.out.println("Here is a list of plants:");
        // Retrieve the singleton instance of the Inventory class and print its contents
        Inventory.getInstance().printInventory();
    }
}