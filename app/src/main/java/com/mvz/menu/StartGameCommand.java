package com.mvz.menu;

import com.mvz.*;
import com.mvz.serialization.*;
import com.mvz.thread.ThreadManager;

import java.util.Scanner;

// Class for starting the game, including loading a saved game or creating a new one
public class StartGameCommand implements Command {
    private Player player;                      // Reference to the player object
    private Game game;                          // Reference to the game object
    private Scanner scanner;                    // Scanner for reading user input
    private ThreadManager threadManager;        // Manager for game threads
    private final int STARTING_SUN = 100000;    // Starting amount of sun in the game

    // Initializes player, scanner, and thread manager
    public StartGameCommand(Player player, Scanner scanner) {
        this.player = player;
        this.scanner = scanner;
        this.threadManager = ThreadManager.getInstance();
    }

    // Execute the start game command
    @Override
    public void execute() {
        boolean validChoice = false;
        // Stop all active threads before starting a new game
        threadManager.stopThreads();

        // Loop until a valid choice is made
        while (!validChoice) {
            // Ask the user if they want to load a saved game
            System.out.println("");
            System.out.println("Do you want to load a saved game? (yes/no)");
            String response = scanner.nextLine().trim().toLowerCase();

            if (response.equals("yes")) {
                // Attempt to load a saved game
                Load load = new Load();
                game = load.performLoad();
                if (game == null) {
                    System.out.println("Failed to load the game. Please check the file path or ensure the file is correct.");
                    continue;
                }
                System.out.println("Game loaded successfully.");
                validChoice = true;
            } else if (response.equals("no")) {
                // Start a new game
                game = new Game(player);
                System.out.println("New game started.");
                validChoice = true;
            } else {
                // Inform the user of an invalid input
                System.out.println("Invalid input. Please type 'yes' or 'no'.");
            }
            // Clear the console for a better user experience
            System.out.print("\033[H\033[2J");
            System.out.flush();
        }

        // Start the game processes
        if (game != null) {
            player.customizeDeck(scanner);
            // Clear the console for a better user experience
            System.out.print("\033[H\033[2J");
            System.out.flush();
            
            //Set the initial amount of sun
            Sun.setSun(STARTING_SUN);
            // Start the game threads
            threadManager.startThreads(game, scanner);
            System.out.println("Game processes started!");
        }
    }

    // Getters for game states
    public Map getMap() { return game.getMap(); }
    public int getGameSun() { return Sun.getSun(); }
    public long getGameTime() { return System.currentTimeMillis(); }
    public Player getPlayer() { return player; }

    // Setters for game states
    public void setMap(Map map) { this.game.setMap(map); }
    public void setGameSun(int sun) { Sun.setSun(sun); }
    public void setGameTime(long time) { /* Dipikirkan nanti */ }
    public void setPlayer(Player player) { this.player = player; }
}
