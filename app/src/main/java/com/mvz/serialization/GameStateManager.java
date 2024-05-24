package com.mvz.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mvz.Game;

import java.io.FileWriter;
// import java.io.FileReader;
import java.io.IOException;

public class GameStateManager {
    private Gson gson;
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";

    public GameStateManager() {
        this.gson = new GsonBuilder()
                .create();
    }

    public boolean saveGameState(Game game, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(game, writer);
            return true;
        } catch (IOException e) {
            System.err.println(ANSI_RED + "Error saving game state: " + e.getMessage() + ANSI_RESET);
            return false;
        }
    }
}
