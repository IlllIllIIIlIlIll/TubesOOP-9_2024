package com.mvz.menu;

import com.mvz.*;

import java.util.InputMismatchException;
import java.util.Scanner;

public class PauseMenu implements Menu {
    private Game game;
    private Scanner scanner;

    public PauseMenu(Game game, Scanner scanner) {
        this.game = game;
        this.scanner = scanner;
    }

    @Override
    public void displayMenu() {
        boolean running = true;

        while (running) {
            System.out.println("\nGame is paused.");
            System.out.println("1. Continue Game");
            System.out.println("2. Exit Game");
            System.out.print("Choose an option: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Clear the buffer
                switch (choice) {
                    case 1:
                        game.resumeGame();
                        System.out.println("Game resumed.");
                        running = false;
                        break;
                    case 2:
                        new ExitMenu(game, scanner).displayMenu();
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear the invalid input from the buffer
            }
        }
    }
}
