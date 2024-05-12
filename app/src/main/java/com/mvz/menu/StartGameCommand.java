package com.mvz.menu;

import com.mvz.*;
import com.mvz.serialization.*;
import com.mvz.thread.ThreadManager;

import java.util.Scanner;

public class StartGameCommand implements Command {
    private GameStateManager gameStateManager;
    private Player player;
    private Game game;

    public StartGameCommand(Player player) {
        this.player = player;
        this.gameStateManager = new GameStateManager();
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        boolean validChoice = false;
        ThreadManager threadManager;

        while (!validChoice) {
            System.out.println("Do you want to load a saved game? (yes/no)");
            String response = scanner.nextLine().trim().toLowerCase();

            if (response.equals("yes")) {
                // Load game dari file json yang disimpan
                game = gameStateManager.loadGameState("savegame.json");
                if (game == null) {
                    System.out.println("Failed to load the game. Please check the file path or ensure the file is correct.");
                    continue;  // Prompt the user again
                }
                System.out.println("Game loaded successfully.");
                validChoice = true;  // Keluar dari loop
            } else if (response.equals("no")) {
                // New game dengan initial sun 25
                game = new Game();
                System.out.println("New game started.");
                validChoice = true;  // Keluar dari loop
            } else {
                System.out.println("Invalid input. Please type 'yes' or 'no'.");
            }
        }

        // Memulai game
        if (game != null) {
            // player.customizeDeck();
            // long previousElapsedTime = loadElapsedTime();

            threadManager = new ThreadManager(game);
            // Start the threads
            threadManager.startThreads();
            System.out.println("Game processes started!");
        }

        scanner.close();
    }
    

        // Getters for game state
        public Map getMap() { return game.getMap(); }
        public int getGameSun() { return Sun.getSun(); }
        public long getGameTime() { return System.currentTimeMillis(); } // Example of getting current game time
        public Player getPlayer() { return player; } // Menyimpan deck juga
    
        // Setters for game state
        public void setMap(Map map) { this.game.setMap(map); }
        public void setGameSun(int sun) { Sun.setSun(sun); } 
        public void setGameTime(long time) { /* Dipikirkan nanti */ }
        public void setPlayer(Player player) { this.player = player; }
}
