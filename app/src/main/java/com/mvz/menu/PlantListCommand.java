package com.mvz.menu;
import com.mvz.*;

public class PlantListCommand implements Command {
    @Override
    public void execute() {
        System.out.println("Here is a list of plants:");
        Inventory.getInstance().printInventory();
    }
}