package com.mvz;

import java.util.ArrayList;

public class Tile {
    // Coordinates of the tile
    private Integer x;
    private Integer y;
    // List of characters (e.g., plants or zombies) that occupy the tile
    private ArrayList<Character> owners; 
    // Boolean indicating if the tile is aquatic
    private Boolean isAquatic;

    // Constructor to initialize the tile with coordinates and its aquatic status
    public Tile(Integer x, Integer y, Boolean isAquatic){
        this.x = x;
        this.y = y;
        owners = new ArrayList<>();
        this.isAquatic = isAquatic;
    }

    // Getter method for the x-coordinate
    public int getX(){
        return x;
    }

    // Getter method for the y-coordinate
    public int getY(){
        return y;
    }

    // Method to get the position as an array of x and y coordinates
    public int[] getPosition() {
        return new int[]{x, y};
    }

    // Getter method for the aquatic status of the tile
    public boolean getIsA(){
        return isAquatic;
    }

    // Getter method for the list of owners on the tile
    public ArrayList<Character> getOwners(){
        return owners;
    }

    // Method to add a character (plant or zombie) to the tile
    public void addOwner(Character owner){
        this.owners.add(owner);
    }

    // Method to remove a character (plant or zombie) from the tile
    public void removeOwner(Character owner){
        this.owners.remove(owner);
    }

    // Method to check if there are no owners on the tile
    public boolean isOwnersEmpty(){
        return owners.isEmpty();
    }
    
    // Setter method for the x-coordinate
    public void setX(Integer x){
        this.x = x;
    }

    // Setter method for the y-coordinate
    public void setY(Integer y){
        this.y = y;
    }

    // Emergency method to toggle the aquatic status of the tile
    public void swap(){
        isAquatic  = !isAquatic;
    }

}
