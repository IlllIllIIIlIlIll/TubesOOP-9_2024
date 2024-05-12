package com.mvz.menu;

import com.mvz.*;

import java.util.Scanner;

public class PauseMenu implements Menu{
    private Game game;

    public PauseMenu(Game game) {
        this.game = game;
    }

    @Override
    public void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\nGame is paused.");
            System.out.println("1. Continue Game");
            System.out.println("2. Exit Game");
            System.out.println("5. Return to Game Menu");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    game.resumeGame();
                    System.out.println("Game resumed.");
                    running = false;
                    break;
                case 2:
                    new ExitMenu(game).displayMenu();
                    running = false;
                    break;
                case 5:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
        scanner.close();
    }
}
