package com.mvz;

import java.util.ArrayList;
import java.util.List;

public class Map {
    private Float zombieProbability;
    private Integer tileMatrix;
    private List<Plant> plantOnTile;
    private List<Zombie> zombieOnTile;
    private Tile[][] tile;
        
    public Map(){
        this.tileMatrix = 9 * 6; // Assuming the size of the map is 9x6 tiles
        this.tile = new Tile[9][6]; // Initialize the tile matrix
        this.plantOnTile = new ArrayList<>();
        this.zombieOnTile = new ArrayList<>();
    }

    public void placeZombie(Zombie z){
        // Add the zombie to the list of zombies on the map
        this.zombieOnTile.add(z);
        // You would also need to add code here to place the zombie on a specific tile
    }

    public void placePlant(Plant p){
        // Add the plant to the list of plants on the map
        this.plantOnTile.add(p);
        // You would also need to add code here to place the plant on a specific tile
    }

    // tiap detik terupdate per masing2 jalur (semangat ya)
    public void setPosition(){

    }

    public void winGame(){
        // Code to handle winning the game
        System.out.println("Congratulations, you've won the game!");
    }

    public void loseGame(){
        // Code to handle losing the game
        System.out.println("Sorry, you've lost the game.");
    }
}
