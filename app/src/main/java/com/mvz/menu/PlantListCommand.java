package com.mvz.menu;
import com.mvz.*;

public class PlantListCommand implements Command {
    @Override
    public void execute() {
        Inventory.getInstance().printPlant();
    }
}