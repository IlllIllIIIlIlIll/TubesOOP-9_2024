package com.mvz.menu;

import com.mvz.*;
import com.mvz.serialization.*;

import java.util.Scanner;

// Managing exit menu options in the game
public class ExitMenu implements Menu{
    private Game game;

    // Initializes the game object
    public ExitMenu(Game game) {
        this.game = game;
    }

    @Override
    //Display the exit menu and handle user choices
    public void displayMenu() {
        System.out.println("\nExit Menu");
        System.out.println("1. Save Game");
        System.out.println("2. Quit without Saving");
        System.out.println("5. Return to Pause Menu");
        System.out.print("Choose an option: ");

        // Scanner object for reading user input
        Scanner scanner = new Scanner(System.in);
        // Read the user's choice
        int choice = scanner.nextInt();
        // Process the user's choice
        switch (choice) {
            case 1:
                // Perform a save operation
                new Save(game).performSave();
                break;
            case 2:
                // Exit the game without saving
                System.out.println("Exiting without saving...");
                System.exit(0);
                break;
            case 5:
                // Return to the pause menu
                new PauseMenu(game).displayMenu();
                break;
            default:
                // Inform the user of an invalid option
                System.out.println("Invalid option. Please try again.");
        }
        // Close the scanner object
        scanner.close();
    }
}
