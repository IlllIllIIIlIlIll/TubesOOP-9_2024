package com.mvz;

import java.util.ArrayList;
import java.util.List;

public class Map {
    private List<Plant> plantOnTile;
    private List<Zombie> zombieOnTile;
    private Tile[][] tile;
    private List<List<Zombie>> zombiesByY; // A list of lists of zombies, indexed by y-coordinate
        
    public Map(){
        tile = new Tile[11][6]; // Initialize the tile matrix
        plantOnTile = new ArrayList<>();
        zombieOnTile = new ArrayList<>();
        zombiesByY = new ArrayList<>();
        for (int i = 0; i < 6; i++) { // Assuming there are 6 y-coordinates
            this.zombiesByY.add(new ArrayList<>());
        }

        // Initialize the tiles
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 6; j++) {
                boolean isAquatic = (j == 2 || j == 3); // Set isAquatic to true for y=3 and y=4
                tile[i][j] = new Tile(i, j, "", isAquatic); // Replace "owner" with the actual owner
            }
        }
    }

    public Tile getTile(int x, int y) {
        return this.tile[x][y];
    }

    public int getNumberOfRows() {
        return this.tile[0].length;
    }

    public int getNumberOfColumns() {
        return this.tile.length;
    }

    public void printMap() {
        for (int j = 0; j < getNumberOfRows(); j++) {
            for (int i = 0; i < getNumberOfColumns(); i++) {
                Tile currentTile = getTile(i, j);
                if (currentTile.getOwners().contains("Zombie")) {
                    System.out.print("[Z] "); // Each tile with a zombie is represented by [Z]
                } else {
                    System.out.print("[ ] "); // Each empty tile is represented by [ ]
                }
            }
            System.out.println(); // Move to the next line after printing each column
        }
        System.out.println();
        System.out.println();
    }
    

    // moves zombies +1 tile
    public void setPosition(){

    }
    public void placeZombie(Zombie z, int y) {
        // Add the zombie to the list of zombies on the map
        this.zombieOnTile.add(z);
        // Add the zombie to the list for the specific y-coordinate
        this.zombiesByY.get(y).add(z);
        // Place the zombie on a specific tile
        int x = z.getTile().getX(); // Assuming the Zombie class has a getTile() method that returns the Tile where the zombie is placed
        tile[x][y].addOwner("Zombie"); // Add "Zombie" to the owners of the tile
    }

    public void placePlant(Plant p){
        // Add the plant to the list of plants on the map
        this.plantOnTile.add(p);
        // You would also need to add code here to place the plant on a specific tile
    }

}
