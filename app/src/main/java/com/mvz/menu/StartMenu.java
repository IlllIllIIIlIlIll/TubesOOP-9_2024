package com.mvz.menu;

import java.util.Scanner;
import com.mvz.*;

public class StartMenu implements Menu {
    private Scanner scanner;
    private Command startCommand;
    private Command helpCommand;
    private Command plantListCommand;
    private Command zombieListCommand;

    public StartMenu(Player player, Scanner scanner) {
        this.scanner = scanner;
        // Initialize commands
        this.startCommand = new StartGameCommand(player, scanner);
        this.helpCommand = new HelpCommand();
        this.plantListCommand = new PlantListCommand();
        this.zombieListCommand = new ZombieListCommand();
    }

    @Override
    public void displayMenu() {
        boolean running = true;
        while (running) {
            printMenuOptions(); 
            try {
                if (scanner.hasNextInt()) {
                    int choice = scanner.nextInt();
                    scanner.nextLine(); 
                    switch (choice) {
                        case 1:
                            // better user experience
                            System.out.print("\033[H\033[2J");
                            System.out.flush();

                            startCommand.execute();
                            running = false; 
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
                            // better user experience
                            System.out.print("\033[H\033[2J");
                            System.out.flush();

                            System.out.println("Exiting game...");
                            running = false; 
                            synchronized (scanner) {
                                scanner.close();
                            }
                            System.exit(0);
                            break;
                        default:
                            System.out.println("Invalid option. Please try again.");
                    }
                } else {
                    System.out.println("Please enter a valid number.");
                    scanner.next(); 
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                scanner.nextLine(); 
            }
        }
    }

    protected void printMenuOptions() {
        System.out.println("Welcome to Michael vs. Lalapan!");
        System.out.println("1. Start Game");
        System.out.println("2. Help");
        System.out.println("3. List Plants");
        System.out.println("4. List Zombies");
        System.out.println("5. Close Game");
        System.out.print("Choose an option: ");
    }
}
