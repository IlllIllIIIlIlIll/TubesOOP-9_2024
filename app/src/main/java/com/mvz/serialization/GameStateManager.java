package com.mvz.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mvz.Game;
import com.mvz.Zombie;
import com.mvz.Plant;

import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

// Manages the serialization and deserialization of game states, including custom handling for Zombie and Plant classes
public class GameStateManager {
    // Gson instance configured with custom serializers for Zombie and Plant classes
    private Gson gson;

    // Constructor initializes the Gson builder with custom serializers for Zombie and Plant classes
    public GameStateManager() {
        this.gson = new GsonBuilder()
                .registerTypeAdapter(Zombie.class, new ZombieInstanceCreator())
                .registerTypeAdapter(Plant.class, new PlantInstanceCreator())
                .create();
    }

    // Saves the current game state to a file, returning true on success and false otherwise
    public boolean saveGameState(Game game, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            // Serializes the game object to JSON and writes it to the specified file path
            gson.toJson(game, writer);
            return true;
        } catch (IOException e) {
            // Logs an error message and returns false if an I/O exception occurs
            System.err.println("Error saving game state: " + e.getMessage());
            return false;
        }
    }

    // Loads a saved game state from a file, initializing executors and returning the loaded game object
    public Game loadGameState(String filePath) {
        try (FileReader reader = new FileReader(filePath)) {
            // Deserializes the JSON content from the file into a Game object
            Game game = gson.fromJson(reader, Game.class);
            // Initializes executors for the map after loading the game state
            game.getMap().initExecutors();
            return game;
        } catch (IOException e) {
            // Logs an error message and returns null if an I/O exception occurs
            System.err.println("Error loading game state: " + e.getMessage());
            return null;
        }
    }
}
