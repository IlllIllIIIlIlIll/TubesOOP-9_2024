package com.mvz.menu;

import com.mvz.*;

import java.util.InputMismatchException;
import java.util.Scanner;

public class EndGameMenu implements Menu {
    private Player player;
    private Scanner scanner;

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_RED = "\u001B[31m";

    public EndGameMenu(Player player, Scanner scanner) {
        this.player = player;
        this.scanner = scanner;
    }

    @Override
    public void displayMenu() {
        boolean running = true;

        while (running) {
            Ascii.endGameMenuPrint();
            System.out.println(ANSI_CYAN + "Choose your option: " + ANSI_RESET);

            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline left-over
                switch (choice) {
                    case 1:
                        // better user experience
                        System.out.print("\033[H\033[2J");
                        System.out.flush();
                        StartMenu startMenu = new StartMenu(player, scanner);
                        startMenu.displayMenu();
                        running = false;
                        break;
                    default:
                        // better user experience
                        System.out.print("\033[H\033[2J");
                        System.out.flush();
                        System.out.println(ANSI_RED + "Invalid option. Please try again." + ANSI_RESET);
                }
            } catch (InputMismatchException e) {
                // better user experience
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println(ANSI_RED + "Invalid input. Please enter a number." + ANSI_RESET);
                scanner.nextLine(); // Clear the invalid input
            }
        }
    }
}
