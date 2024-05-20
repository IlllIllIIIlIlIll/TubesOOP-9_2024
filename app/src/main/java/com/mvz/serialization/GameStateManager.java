package com.mvz.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mvz.Game;
import com.mvz.Zombie;
import com.mvz.Plant;

import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

public class GameStateManager {
    private Gson gson;

    public GameStateManager() {
        this.gson = new GsonBuilder()
                .registerTypeAdapter(Zombie.class, new ZombieInstanceCreator())
                .registerTypeAdapter(Plant.class, new PlantInstanceCreator())
                .create();
    }

    public boolean saveGameState(Game game, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(game, writer);
            return true;
        } catch (IOException e) {
            System.err.println("Error saving game state: " + e.getMessage());
            return false;
        }
    }

    public Game loadGameState(String filePath) {
        try (FileReader reader = new FileReader(filePath)) {
            Game game = gson.fromJson(reader, Game.class);
            game.getMap().initExecutors();
            return game;
        } catch (IOException e) {
            System.err.println("Error loading game state: " + e.getMessage());
            return null;
        }
    }
}
