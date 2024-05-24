package com.mvz.menu;

import com.mvz.*;

import java.util.InputMismatchException;
import java.util.Scanner;

public class PauseMenu implements Menu {
    private Game game;
    private Scanner scanner;

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_RED = "\u001B[31m";

    public PauseMenu(Game game, Scanner scanner) {
        this.game = game;
        this.scanner = scanner;
    }

    @Override
    public void displayMenu() {
        boolean running = true;

        while (running) {
            Ascii.pauseMenuPrint();
            System.out.println(ANSI_CYAN + "Choose your option: " + ANSI_RESET);

            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Clear the buffer
                switch (choice) {
                    case 1:
                        game.resumeGame();
                        System.out.println(ANSI_CYAN + "Game resumed." + ANSI_RESET);
                        running = false;
                        break;
                    case 2:
                        // better user experience
                        System.out.print("\033[H\033[2J");
                        System.out.flush();
                        new ExitMenu(game, scanner).displayMenu();
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
                scanner.nextLine(); // Clear the invalid input from the buffer
            }
        }
    }
}
