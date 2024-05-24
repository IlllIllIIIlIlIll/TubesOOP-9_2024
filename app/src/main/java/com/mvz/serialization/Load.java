package com.mvz.serialization;

import com.mvz.Game;
import java.io.File;
import java.util.Scanner;

public class Load {
    // GameStateManager instance for managing the serialization and deserialization of game states
    private GameStateManager gameStateManager;

    // Constructor initializes the GameStateManager instance
    public Load() {
        this.gameStateManager = new GameStateManager();
    }

    // Main method to perform the loading process, prompting the user for a filename and attempting to load the game state
    public Game performLoad() {
        // Creates a Scanner to read user input
        Scanner scanner = new Scanner(System.in);
        // Prompts the user to enter the filename of the game they wish to load
        System.out.println("Enter the filename to load your game (do not include path):");
        String filename = scanner.nextLine().trim();
        
        // Validates the entered filename
        if (!isValidFilename(filename)) {
            System.out.println("Invalid filename. Please try again.");
            scanner.close();
            return null;
        }

        // Constructs the full path to the save file within a designated saves directory
        String directoryPath = "saves"; 
        File file = new File(directoryPath, filename + ".json");

        // Checks if the file exists
        if (!file.exists()) {
            System.out.println("File does not exist. Please check the filename and try again.");
            scanner.close();
            return null;
        }

        // Attempts to load the game state from the file
        Game game = gameStateManager.loadGameState(file.getPath());
        if (game != null) {
            System.out.println("Game loaded successfully from " + file.getPath());
        } else {
            System.out.println("Failed to load the game.");
        }
        
        // Closes the scanner and returns the loaded game object
        scanner.close();
        return game;
    }

    // Private utility method to validate the filename according to certain criteria
    private boolean isValidFilename(String filename) {
        // Regular expression to match valid filenames, excluding characters that could cause issues in file paths
        return filename.matches("^[^.\\\\/:*?\"<>|]?[^\\\\/:*?\"<>|]*") && !filename.isEmpty();
    }
}
