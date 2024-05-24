package com.mvz.menu;

import com.mvz.*;
import com.mvz.serialization.*;
import com.mvz.thread.ThreadManager;

import java.util.Scanner;

public class StartGameCommand implements Command {
    private Player player;
    private Game game;
    private Scanner scanner;
    private ThreadManager threadManager;
    private final int STARTING_SUN = 50;

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_RED = "\u001B[31m";

    public StartGameCommand(Player player, Scanner scanner) {
        this.player = player;
        this.scanner = scanner;
        this.threadManager = ThreadManager.getInstance();
    }

    @Override
    public void execute() {
        boolean validChoice = false;
        threadManager.stopThreads();

        while (!validChoice) {
            System.out.println(ANSI_CYAN + "Do you want to load a saved game? (yes/no)" + ANSI_RESET);
            String response = scanner.nextLine().trim().toLowerCase();

            if (response.equals("yes")) {
                // Load load = new Load(scanner);
                // game = load.performLoad();
                if (game == null) {
                    // better user experience
                    System.out.print("\033[H\033[2J");
                    System.out.flush();

                    System.out.println(ANSI_RED + "Failed to load the game. Please ensure the file name is correct." + ANSI_RESET);
                    continue;
                }
                // better user experience
                System.out.print("\033[H\033[2J");
                System.out.flush();

                System.out.println(ANSI_CYAN + "Game loaded successfully." + ANSI_RESET);
                validChoice = true;
            } else if (response.equals("no")) {
                game = new Game(player);
                // better user experience
                System.out.print("\033[H\033[2J");
                System.out.flush();

                System.out.println(ANSI_CYAN + "New game created." + ANSI_RESET);
                validChoice = true;
            } else {
                System.out.println(ANSI_RED + "Invalid input. Please type 'yes' or 'no'." + ANSI_RESET);
            }
        }

        // Start the game
        if (game != null) {
            game.getPlayer().customizeDeck(scanner);
            // better user experience
            System.out.print("\033[H\033[2J");
            System.out.flush();
            
            Sun.setSun(STARTING_SUN);
            threadManager.startThreads(game, scanner);
            System.out.println(ANSI_CYAN + "Game processes started!" + ANSI_RESET);
        }
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
