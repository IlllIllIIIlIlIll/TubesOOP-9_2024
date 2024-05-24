package com.mvz.menu;

import com.mvz.*;

import java.util.Scanner;

public class PauseMenu implements Menu {
    private Game game;

    // Constructor initializes the game object
    public PauseMenu(Game game) {
        this.game = game;
    }

    // Display the pause menu and handle user choices
    @Override
    public void displayMenu() {
        // Create a new scanner object for reading user input
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            // Display the pause menu options
            System.out.println("\nGame is paused.");
            System.out.println("1. Continue Game");
            System.out.println("2. Exit Game");
            System.out.print("Choose an option: ");

            // Read the user's choice
            int choice = scanner.nextInt();

            // Process the user's choice
            switch (choice) {
                case 1:
                    // Resume the game
                    game.resumeGame();
                    System.out.println("Game resumed.");
                    running = false;    // Exit the loop
                    break;
                case 2:
                    // Exit the game
                    new ExitMenu(game).displayMenu();
                    running = false;    // Exit the loop
                    break;
                default:
                    // Inform the user of an invalid option
                    System.out.println("Invalid option. Please try again.");
            }
        }
        scanner.close();
    }
}
