package com.mvz.serialization;

import com.mvz.*;

import java.io.File;
import java.util.Scanner;

public class Save {
    private GameStateManager gameStateManager;
    private Game game;
    private Scanner scanner;
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_RED = "\u001B[31m";

    public Save(Game game, Scanner scanner) {
        this.scanner = scanner;
        this.game = game;
        this.gameStateManager = new GameStateManager();
    }

    public void performSave() {
        System.out.println(ANSI_CYAN + "Enter the filename to save your game (do not include path):" + ANSI_RESET);
        String filename = scanner.nextLine().trim();
        if (!isValidFilename(filename)) {
            System.out.println(ANSI_RED + "Invalid filename. Please try again." + ANSI_RESET);
            return;
        }

        String directoryPath = "saves"; 
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdirs(); 
        }

        File file = new File(directory, filename + ".json");
        
        if (gameStateManager.saveGameState(game, file.getPath())) {
            System.out.println(ANSI_CYAN + "Game saved successfully to " + file.getPath() + ANSI_RESET);
        } else {
            System.out.println(ANSI_RED + "Failed to save the game." + ANSI_RESET);
        }
        game.endGame(scanner);
    }

    private boolean isValidFilename(String filename) {
        return filename.matches("^[^.\\\\/:*?\"<>|]?[^\\\\/:*?\"<>|]*") && !filename.isEmpty();
    }
}
