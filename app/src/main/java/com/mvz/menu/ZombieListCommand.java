package com.mvz.menu;

public class ZombieListCommand implements Command {
    @Override
    // Execute the command, printing the list of zombies
    public void execute() {
        System.out.println("Here is a list of zombies:");
    }
}