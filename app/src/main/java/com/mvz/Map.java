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
        String watercolor = "\033[1;34m";
        String landcolor = "\033[0;32m";
        String reset = "\033[0m"; // Reset the color
    
        System.out.println("Sun value: " + Sun.getSun());
        for (int j = 0; j < getNumberOfRows(); j++) {
            for (int i = 0; i < getNumberOfColumns(); i++) {
                Tile currentTile = getTile(i, j);
                String color;
                if (j == 2 || j == 3) {
                    color = watercolor;
                } else {
                    color = landcolor;
                }
                System.out.print(color + "[");
                if (currentTile.getOwners().size() > 0) {
                    for (int k = 0; k < 5; k++) {
                        if (k < currentTile.getOwners().size()) {
                            System.out.print(currentTile.getOwners().get(k).getName().charAt(0));
                        } else {
                            System.out.print(" "); 
                        }
                    }
                } else {
                    System.out.print("     "); 
                }
                System.out.print("]" + reset); 
            }
            System.out.println(); 
        }
        System.out.println();
        System.out.println();
    }
    
    

    public void setPosition() {
        for (Zombie z : zombieOnTile) {
            if (z.getCH()) {
                if (z.getCM()) {
                    z.move(); 
                    z.setCM(); 
                } else {
                    z.setCM(); 
                    continue; 
                }
            }
    
            Tile currentTile = z.getTile();
            int x = currentTile.getX();
            int y = currentTile.getY();
            if (x - 1 >= 0) { 
                Tile nextTile = getTile(x - 1, y);
                for (Character owner : nextTile.getOwners()) {
                    
                    if (owner instanceof Plant && ((Plant) owner).getHealth() <= 0) {
                        
                        nextTile.removeOwner(owner);
                    }
                }
                
                boolean hasPlant = false;
                for (Character owner : nextTile.getOwners()) {
                    if (owner instanceof Plant) {
                        hasPlant = true;
                        break;
                    }
                }
                if (!hasPlant) { 
                    currentTile.removeOwner(z);
                    nextTile.addOwner(z);
                    z.setTile(nextTile); 
                }
            } else {
                // Panggil fungsi defeated
            }
        }
    }
    

    
    public void placeZombie(Zombie z, int y) {
        // Add zombie to list of current zombies in map
        this.zombieOnTile.add(z);
        // Add zombie to the list of same y coordinate zombies
        this.zombiesByY.get(y).add(z);
        
        // Insert zombie to the corresponding tile
        int x = z.getTile().getX(); 
        tile[x][y].addOwner(z); 
    }
    
    public void placePlant(Plant p, int x, int y) {
        // Lilypad check
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

            // WRONG IMPLEMENTATION! FIX ASAP
            if (containsLilypad) {
                p.setHealth(p.getHealth() + lilypad.getHealth()); 
            }


            // Same as zombie
            targetTile.addOwner(p);
            p.setTile(targetTile); 
            this.plantOnTile.add(p);
        } else {
            System.out.println("Cannot place the plant on a tile that already contains a plant.");
        }
    }    

}
