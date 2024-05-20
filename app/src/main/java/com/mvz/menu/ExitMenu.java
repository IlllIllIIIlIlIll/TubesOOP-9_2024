package com.mvz.menu;

import com.mvz.*;
import com.mvz.serialization.*;

import java.util.Scanner;

public class ExitMenu implements Menu{
    private Game game;
    private Scanner scanner;

    public ExitMenu(Game game, Scanner scanner) {
        this.game = game;
        this.scanner = scanner;
    }

    @Override
    public void displayMenu() {
        System.out.println("\nExit Menu");
        System.out.println("1. Save Game");
        System.out.println("2. Quit without Saving");
        System.out.println("5. Return to Pause Menu");
        System.out.print("Choose an option: ");

        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1:
                new Save(game, scanner).performSave();
                break;
            case 2:
                System.out.println("Exiting without saving...");
                System.exit(0);
                break;
            case 5:
                new PauseMenu(game, scanner).displayMenu();
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }
}
