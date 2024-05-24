package com.mvz.menu;

import java.util.Scanner;
import com.mvz.*;

public class StartMenu implements Menu {
    private Scanner scanner;
    private Command startCommand;
    private Command helpCommand;
    private Command plantListCommand;
    private Command zombieListCommand;

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_RED = "\u001B[31m";

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
                            // better user experience
                            System.out.print("\033[H\033[2J");
                            System.out.flush();
                            helpCommand.execute();
                            break;
                        case 3:
                            // better user experience
                            System.out.print("\033[H\033[2J");
                            System.out.flush();
                            plantListCommand.execute();
                            break;
                        case 4:
                            // better user experience
                            System.out.print("\033[H\033[2J");
                            System.out.flush();
                            zombieListCommand.execute();
                            break;
                        case 5:
                            // better user experience
                            System.out.print("\033[H\033[2J");
                            System.out.flush();

                            System.out.println(ANSI_CYAN + "Exiting game..." + ANSI_RESET);
                            running = false; 
                            synchronized (scanner) {
                                scanner.close();
                            }
                            System.exit(0);
                            break;
                        default:
                            // better user experience
                            System.out.print("\033[H\033[2J");
                            System.out.flush();
                            System.out.println(ANSI_RED + "Invalid option. Please try again." + ANSI_RESET);
                    }
                } else {
                    // better user experience
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    System.out.println(ANSI_RED + "Please enter a valid number." + ANSI_RESET);
                    scanner.next(); 
                }
            } catch (Exception e) {
                // better user experience
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println(ANSI_RED + "Error: " + e.getMessage() + ANSI_RESET);
                scanner.nextLine(); 
            }
        }
    }

    protected void printMenuOptions() {
        Ascii.startmenuPrint();
        System.out.println(ANSI_CYAN + "Choose your option: " + ANSI_RESET);
    }
}
