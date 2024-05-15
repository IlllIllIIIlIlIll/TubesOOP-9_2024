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
            System.out.println("");
            System.out.println("Do you want to load a saved game? (yes/no)");
            String response = scanner.nextLine().trim().toLowerCase();

            if (response.equals("yes")) {
                game = gameStateManager.loadGameState("savegame.json");
                if (game == null) {
                    System.out.println("Failed to load the game. Please check the file path or ensure the file is correct.");
                    continue;  
                }
                System.out.println("Game loaded successfully.");
                validChoice = true;  
            } else if (response.equals("no")) {
                game = new Game();
                System.out.println("New game started.");
                validChoice = true; 
            } else {
                System.out.println("Invalid input. Please type 'yes' or 'no'.");
            }
            // better user experience
            System.out.print("\033[H\033[2J");
            System.out.flush();
        }

        // Memulai game
        if (game != null) {
            player.customizeDeck();
            // long previousElapsedTime = loadElapsedTime();

            // better user experience
            System.out.print("\033[H\033[2J");
            System.out.flush();
            
            threadManager = new ThreadManager(game, scanner);
            // Start the threads
            threadManager.startThreads();
            System.out.println("Game processes started!");
            
        }
        // scanner ini jangan ditutup thx
        // scanner.close();
    }
    

        // Getters for game state
        public Map getMap() { return game.getMap(); }
        public int getGameSun() { return Sun.getSun(); }
        public long getGameTime() { return System.currentTimeMillis(); } 
        public Player getPlayer() { return player; } 
    
        // Setters for game state
        public void setMap(Map map) { this.game.setMap(map); }
        public void setGameSun(int sun) { Sun.setSun(sun); } 
        public void setGameTime(long time) { /* Dipikirkan nanti */ }
        public void setPlayer(Player player) { this.player = player; }
}
