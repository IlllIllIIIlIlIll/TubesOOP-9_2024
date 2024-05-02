package com.mvz;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Random;

public class Map {
    private Float zombieProbability;
    private List<Plant> plantOnTile;
    private List<Zombie> zombieOnTile;
    private Tile[][] tile;
    private Timer timer;
    private Random random;
    private List<Zombie> zombieTypes;
        
    public Map(){
        this.tile = new Tile[9][6]; // Initialize the tile matrix
        this.plantOnTile = new ArrayList<>();
        this.zombieOnTile = new ArrayList<>();
    }

    public void printMap() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 6; j++) {
                System.out.print("[ ] "); // Each tile is represented by [ ]
            }
            System.out.println(); // Move to the next line after printing each row
        }
    }

    public void placePlant(Plant p){
        // Add the plant to the list of plants on the map
        this.plantOnTile.add(p);
        // You would also need to add code here to place the plant on a specific tile
    }

}
