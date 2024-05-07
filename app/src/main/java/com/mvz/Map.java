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
                tile[i][j] = new Tile(i, j, isAquatic); // Replace "owner" with the actual owner
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
                boolean hasZombie = false;
                for (Character owner : currentTile.getOwners()) {
                    if (owner instanceof Zombie) {
                        hasZombie = true;
                        break;
                    }
                }
                if (hasZombie) {
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

    public void setPosition() {
        for (Zombie z : zombieOnTile) {
            if (z.getCH()) {
                if (z.getCM()) {
                    z.move(); // Let the zombie move
                    z.setCM(); // Change the state of canMove
                } else {
                    z.setCM(); // Change the state of canMove
                    continue; // Skip this iteration if the zombie can't move
                }
            }
    
            Tile currentTile = z.getTile();
            int x = currentTile.getX();
            int y = currentTile.getY();
            if (x - 1 >= 0) { // Check if the zombie can move to the left
                Tile nextTile = getTile(x - 1, y);
                for (Character owner : nextTile.getOwners()) {
                    // Now you can access each owner on the next tile
                    if (owner instanceof Plant && ((Plant) owner).getHealth() <= 0) {
                        // If the owner is a plant and it's dead, remove it from the tile
                        nextTile.removeOwner(owner);
                    }
                }
                // The zombie now moves to the next tile
                boolean hasPlant = false;
                for (Character owner : nextTile.getOwners()) {
                    if (owner instanceof Plant) {
                        hasPlant = true;
                        break;
                    }
                }
                if (!hasPlant) { // Check again if the next tile doesn't contain a plant
                    currentTile.removeOwner(z);
                    nextTile.addOwner(z);
                    z.setTile(nextTile); // Set the new tile for the zombie
                }
            } else {
                // If a zombie reaches x = 0, call the winGame function in the Game class
                // game.winGame(); // Uncomment this line if you have a winGame method in the Game class
            }
        }
    }
    

    
    public void placeZombie(Zombie z, int y) {
        // Add the zombie to the list of zombies on the map
        this.zombieOnTile.add(z);
        // Add the zombie to the list for the specific y-coordinate
        this.zombiesByY.get(y).add(z);
        // Place the zombie on a specific tile
        int x = z.getTile().getX(); // Assuming the Zombie class has a getTile() method that returns the Tile where the zombie is placed
        tile[x][y].addOwner(z); // Add the Zombie object to the owners of the tile
    }
    
    public void placePlant(Plant p, int x, int y) {
        // Check if the tile is empty or contains a Lilypad
        Tile targetTile = getTile(x, y);
        boolean containsLilypad = false;
        Plant lilypad = null;
        for (Character owner : targetTile.getOwners()) {
            if (owner.getName().equals("Lilypad")) {
                containsLilypad = true;
                lilypad = (Plant) owner; // Cast the Character to a Plant
                break;
            }
        }
        if (targetTile.getOwners().isEmpty() || containsLilypad) {
            // If the tile contains a Lilypad, increase the health of the plant
            if (containsLilypad) {
                p.setHealth(p.getHealth() + lilypad.getHealth()); // Assuming the Plant class has a setHealth() method that sets the health of the plant
            }
            // Place the plant on the tile
            targetTile.addOwner(p); // Add the Plant object to the owners of the tile
            p.setTile(targetTile); // Set the new tile for the plant
            // Add the plant to the list of plants on the map
            this.plantOnTile.add(p);
        } else {
            System.out.println("Cannot place the plant on a tile that already contains a plant.");
        }
    }    

}
