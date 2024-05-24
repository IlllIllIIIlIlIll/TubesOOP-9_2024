package com.mvz.menu;

import com.mvz.*;
import com.mvz.serialization.*;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ExitMenu implements Menu{
    private Game game;
    private Scanner scanner;

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_RED = "\u001B[31m";

    public ExitMenu(Game game, Scanner scanner) {
        this.game = game;
        this.scanner = scanner;
    }

    @Override
    public void displayMenu() {
        Ascii.exitMenuPrint();
        System.out.println(ANSI_CYAN + "Choose your option: " + ANSI_RESET);

        try {
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    // better user experience
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    new Save(game, scanner).performSave();
                    break;
                case 2:
                    // better user experience
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    System.out.println(ANSI_CYAN + "Exiting without saving..." + ANSI_RESET);
                    System.exit(0);
                    break;
                case 3:
                    // better user experience
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    new PauseMenu(game, scanner).displayMenu();
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
