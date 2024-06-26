package com;

import java.util.Scanner;

import com.mvz.menu.StartMenu;
import com.mvz.Menu;
import com.mvz.Player;
import com.mvz.Ascii;

public class Main {
    public static void main(String[] args) {
        // better user experience
        System.out.print("\033[H\033[2J");
        System.out.flush();
        
        Ascii.mainPrint();

        System.out.print("\033[H\033[2J");
        System.out.flush();
        
        Player favian = new Player("Favian");
        Scanner scanner = new Scanner(System.in);
        Menu startMenu = new StartMenu(favian, scanner); 
        startMenu.displayMenu();
    }
}