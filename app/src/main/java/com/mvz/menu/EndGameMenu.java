package com.mvz.menu;

import com.mvz.*;
public class EndGameMenu {
    private Menu currentMenu;

    public void setCurrentMenu(StartMenu menu) {
        this.currentMenu = menu;
    }

    public void displayMenu() {
        if (currentMenu != null) {
            currentMenu.displayMenu();
        }
    }
}