package com.mvz.menu;

public class ZombieListCommand implements Command {
    @Override
    public void execute() {
        System.out.println("Here is a list of zombies:");
    }
}