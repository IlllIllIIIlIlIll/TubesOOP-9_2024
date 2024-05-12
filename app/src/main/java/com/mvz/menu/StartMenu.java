package com.mvz.menu;

import java.util.Scanner;

import com.mvz.*;

public class StartMenu implements Menu{
    private Scanner scanner;
    private Command startCommand;
    private Command helpCommand;
    private Command plantListCommand;
    private Command zombieListCommand;

    public StartMenu(Player player) {
        this.scanner = new Scanner(System.in);
        // Initialize commands
        this.startCommand = new StartGameCommand(player);
        this.helpCommand = new HelpCommand();
        this.plantListCommand = new PlantListCommand();
        this.zombieListCommand = new ZombieListCommand();
        
    }

    @Override
    public void displayMenu() {
        boolean running = true;
        while (running) {
            printMenuOptions(); // Method to print menu options
            try {
                if (scanner.hasNextInt()) {
                    int choice = scanner.nextInt();
                    switch (choice) {
                        case 1:
                            startCommand.execute();
                            // After starting the game, assume we're done with the menu for now
                            System.out.println("Press any key to return to the menu...");
                            System.in.read(); // Wait for user to press any key
                            break;
                        case 2:
                            helpCommand.execute();
                            break;
                        case 3:
                            plantListCommand.execute();
                            break;
                        case 4:
                            zombieListCommand.execute();
                            break;
                        case 5:
                            System.out.println("Exiting game...");
                            running = false; // Exit the loop and end the program
                            break;
                        default:
                            System.out.println("Invalid option. Please try again.");
                    }
                } else {
                    System.out.println("Please enter a valid number.");
                    scanner.next(); // Consume the invalid input
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                scanner.nextLine(); // Clear buffer and handle next input correctly
            }
        }
        scanner.close();
    }

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
