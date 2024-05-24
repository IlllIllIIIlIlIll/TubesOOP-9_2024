package com.mvz.menu;

import com.mvz.*;

import java.util.InputMismatchException;
import java.util.Scanner;

public class EndGameMenu implements Menu {
    private Player player;
    private Scanner scanner;

    // Displaying the end game menu options
    public EndGameMenu(Player player, Scanner scanner) {
        this.player = player;
        this.scanner = scanner;
    }

    @Override
    // Display the end game menu and handle user choices
    public void displayMenu() {
        boolean running = true;

        while (running) {
            // Display menu options
            System.out.println("1. Back to Main Menu");
            // Prompt user for input
            System.out.print("Choose an option: ");

            try {
                // Read integer input from user
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline left-over

                // Process user choice
                switch (choice) {
                    case 1:
                        // Create a new instance of StartMenu and display it
                        StartMenu startMenu = new StartMenu(player);
                        startMenu.displayMenu();
                        running = false;
                        break;
                    default:
                        // Inform the user of an invalid option
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (InputMismatchException e) {
                // Handle invalid input by clearing the scanner buffer and prompting again
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear the invalid input
            }
        }
    }
}
