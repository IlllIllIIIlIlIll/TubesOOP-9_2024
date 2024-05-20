package com.mvz.menu;

import com.mvz.*;

import java.util.InputMismatchException;
import java.util.Scanner;

public class EndGameMenu implements Menu {
    private Player player;
    private Scanner scanner;

    public EndGameMenu(Player player, Scanner scanner) {
        this.player = player;
        this.scanner = scanner;
    }

    @Override
    public void displayMenu() {
        boolean running = true;

        while (running) {
            System.out.println("1. Back to Main Menu");
            System.out.print("Choose an option: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline left-over
                switch (choice) {
                    case 1:
                        StartMenu startMenu = new StartMenu(player, scanner);
                        startMenu.displayMenu();
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear the invalid input
            }
        }
    }
}
