package com.mvz.menu;

public class HelpCommand implements Command {
    @Override
    public void execute() {
        System.out.println("This is the help menu. Here are some tips...");
    }
}