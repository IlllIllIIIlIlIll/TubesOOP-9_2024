package com.mvz.menu;

import com.mvz.*;
import com.mvz.serialization.*;

import java.util.Scanner;

public class ExitMenu implements Menu{
    private Game game;

    public ExitMenu(Game game) {
        this.game = game;
    }

    @Override
    public void displayMenu() {
        System.out.println("\nExit Menu");
        System.out.println("1. Save Game");
        System.out.println("2. Quit without Saving");
        System.out.println("5. Return to Pause Menu");
        System.out.print("Choose an option: ");

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                new Save(game).performSave();
                break;
            case 2:
                System.out.println("Exiting without saving...");
                System.exit(0);
                break;
            case 5:
                new PauseMenu(game).displayMenu();
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }
        scanner.close();
    }
}
