package com.mvz.menu;

import java.util.Scanner;
import com.mvz.*;

public class StartMenu implements Menu {
    private Scanner scanner;            // Scanner for reading user input
    private Command startCommand;       // Command to start the game
    private Command helpCommand;        // Command to display help information
    private Command plantListCommand;   // Command to list available plants
    private Command zombieListCommand;  // Command to list available zombies

    // Initializes the scanner and commands
    public StartMenu(Player player) {
        this.scanner = new Scanner(System.in);
        // Initialize commands with the player and scanner
        this.startCommand = new StartGameCommand(player, scanner);
        this.helpCommand = new HelpCommand();
        this.plantListCommand = new PlantListCommand();
        this.zombieListCommand = new ZombieListCommand();
    }

    @Override
    // Display the start menu and handle user choices
    public void displayMenu() {
        boolean running = true;
        while (running) {
            // Print the menu options
            printMenuOptions(); 
            try {
                // Check if the next input is an integer
                if (scanner.hasNextInt()) {
                    int choice = scanner.nextInt();
                    scanner.nextLine();     // Consume the newline
                    // Process the user's choice
                    switch (choice) {
                        case 1:
                            // Execute the start game command
                            startCommand.execute();
                            running = false; 
                            break;
                        case 2:
                            // Execute the help command
                            helpCommand.execute();
                            break;
                        case 3:
                            // Execute the plant list command
                            plantListCommand.execute();
                            break;
                        case 4:
                            // Execute the zombie list command
                            zombieListCommand.execute();
                            break;
                        case 5:
                            // Exit the game
                            System.out.println("Exiting game...");
                            running = false; 
                            break;
                        default:
                            // Inform the user of an invalid option
                            System.out.println("Invalid option. Please try again.");
                    }
                } else {
                    // Inform the user of invalid input
                    System.out.println("Please enter a valid number.");
                    scanner.next(); 
                }
            } catch (Exception e) {
                // Catch and report any exceptions
                System.out.println("Error: " + e.getMessage());
                scanner.nextLine(); 
            }
        }
    }

    // Protected method to print the menu options
    protected void printMenuOptions() {
        System.out.println("\nWelcome to Plants vs Zombies!");
        System.out.println("1. Start Game");
        System.out.println("2. Help");
        System.out.println("3. List Plants");
        System.out.println("4. List Zombies");
        System.out.println("5. Close Game");
        System.out.print("Choose an option: ");
    }
}
