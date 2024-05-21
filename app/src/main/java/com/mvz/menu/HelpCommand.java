package com.mvz.menu;

public class HelpCommand implements Command {
    @Override
    public void execute() {
        // better user experience
        System.out.print("\033[H\033[2J");
        System.out.flush();
        
        System.out.println("This is the help menu. Here are some tips...");
    }
}