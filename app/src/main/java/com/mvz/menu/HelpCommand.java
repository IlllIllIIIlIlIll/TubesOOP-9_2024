package com.mvz.menu;

public class HelpCommand implements Command {
    @Override
    // Printing out help information
    public void execute() {
        System.out.println("This is the help menu. Here are some tips...");
    }
}