package com.mvz.serialization;

import com.mvz.*;

import java.io.File;
import java.util.Scanner;

public class Save {
    private GameStateManager gameStateManager;
    private Game game;

    public Save(Game game) {
        this.game = game;
        this.gameStateManager = new GameStateManager();
    }

    public void performSave() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the filename to save your game (do not include path):");
        String filename = scanner.nextLine().trim();
        if (!isValidFilename(filename)) {
            System.out.println("Invalid filename. Please try again.");
            scanner.close();
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
        scanner.close();
    }

    private boolean isValidFilename(String filename) {
        return filename.matches("^[^.\\\\/:*?\"<>|]?[^\\\\/:*?\"<>|]*") && !filename.isEmpty();
    }
}
