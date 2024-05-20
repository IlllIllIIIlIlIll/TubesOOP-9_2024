package com.mvz.serialization;

import com.mvz.*;

import java.io.File;
import java.util.Scanner;

public class Save {
    private GameStateManager gameStateManager;
    private Game game;
    private Scanner scanner;

    public Save(Game game, Scanner scanner) {
        this.scanner = scanner;
        this.game = game;
        this.gameStateManager = new GameStateManager();
    }

    public void performSave() {
        System.out.println("Enter the filename to save your game (do not include path):");
        String filename = scanner.nextLine().trim();
        if (!isValidFilename(filename)) {
            System.out.println("Invalid filename. Please try again.");
            return;
        }

        String directoryPath = "saves"; 
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdirs(); 
        }

        File file = new File(directory, filename + ".json");
        
        if (gameStateManager.saveGameState(game, file.getPath())) {
            System.out.println("Game saved successfully to " + file.getPath());
        } else {
            System.out.println("Failed to save the game.");
        }
        game.endGame(scanner);
    }

    private boolean isValidFilename(String filename) {
        return filename.matches("^[^.\\\\/:*?\"<>|]?[^\\\\/:*?\"<>|]*") && !filename.isEmpty();
    }
}
