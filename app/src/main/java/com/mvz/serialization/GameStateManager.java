package com.mvz.serialization;

import com.mvz.*;   
import com.google.gson.Gson;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

public class GameStateManager {
    private Gson gson;

    public GameStateManager() {
        this.gson = new Gson();
    }

    public boolean saveGameState(Game game, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(game, writer);
            return true; // Return true to indicate success
        } catch (IOException e) {
            System.err.println("Error saving game state: " + e.getMessage());
            return false; // Return false to indicate failure
        }
    }

    public Game loadGameState(String filePath) {
        try (FileReader reader = new FileReader(filePath)) {
            return gson.fromJson(reader, Game.class);
        } catch (IOException e) {
            System.err.println("Error loading game state: " + e.getMessage());
            return null;
        }
    }
    
}
