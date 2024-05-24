package com.mvz.serialization;

import com.mvz.*;

import java.io.File;
import java.util.Scanner;

// Class responsible for saving the game state
public class Save {
    private GameStateManager gameStateManager;
    private Game game;

    // Constructor to initialize the game and game state manager
    public Save(Game game) {
        this.game = game;
        this.gameStateManager = new GameStateManager();
    }

    // Method to perform the save operation
    public void performSave() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the filename to save your game (do not include path):");
        String filename = scanner.nextLine().trim();

        // Validate the filename
        if (!isValidFilename(filename)) {
            System.out.println("Invalid filename. Please try again.");
            scanner.close();
            return;
        }

        String directoryPath = "saves";     // Directory to save the game files
        File directory = new File(directoryPath);
        // Create the directory if it does not exist
        if (!directory.exists()) {
            directory.mkdirs(); 
        }

        // Create a file object with the given filename and .json extension
        File file = new File(directory, filename + ".json");
        
        // Save the game state and check the result
        if (gameStateManager.saveGameState(game, file.getPath())) {
            System.out.println("Game saved successfully to " + file.getPath());
        } else {
            System.out.println("Failed to save the game.");
        }
        scanner.close();
    }

    // Method to validate the filename
    private boolean isValidFilename(String filename) {
        // Check for invalid characters and ensure filename is not empty
        return filename.matches("^[^.\\\\/:*?\"<>|]?[^\\\\/:*?\"<>|]*") && !filename.isEmpty();
    }
}
