package com;

import java.util.Scanner;

import com.mvz.menu.StartMenu;
import com.mvz.Menu;
import com.mvz.Player;

public class Main {
    public static void main(String[] args) {
        Player favian = new Player("Favian");
        Scanner scanner = new Scanner(System.in);
        Menu startMenu = new StartMenu(favian, scanner); 
        startMenu.displayMenu();
    }
}