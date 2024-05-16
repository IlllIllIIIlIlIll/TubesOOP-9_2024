package com.mvz.serialization;

import com.mvz.Game;
import java.io.File;
import java.util.Scanner;

public class Load {
    private GameStateManager gameStateManager;

    public Load() {
        this.gameStateManager = new GameStateManager();
    }

    public Game performLoad() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the filename to load your game (do not include path):");
        String filename = scanner.nextLine().trim();
        
        if (!isValidFilename(filename)) {
            System.out.println("Invalid filename. Please try again.");
            scanner.close();
            return null;
        }

        String directoryPath = "saves"; 
        File file = new File(directoryPath, filename + ".json");

        if (!file.exists()) {
            System.out.println("File does not exist. Please check the filename and try again.");
            scanner.close();
            return null;
        }

        Game game = gameStateManager.loadGameState(file.getPath());
        if (game != null) {
            System.out.println("Game loaded successfully from " + file.getPath());
        } else {
            System.out.println("Failed to load the game.");
        }
        
        scanner.close();
        return game;
    }

    private boolean isValidFilename(String filename) {
        return filename.matches("^[^.\\\\/:*?\"<>|]?[^\\\\/:*?\"<>|]*") && !filename.isEmpty();
    }
}
