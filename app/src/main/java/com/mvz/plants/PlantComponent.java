package com.mvz.plants;

import com.mvz.Plant;

// Defines the behavior of components that can be added to a Lilypad plant in the game
public interface PlantComponent{
    boolean canBePlacedOnLilyPad();     // Checks if a plant can be placed on a Lilypad
    boolean addOnLilypad(Plant plant);  // Adds a plant to a Lilypad, returning true if successful
    void removeOnLilypad(Plant plant);  // Removes a plant from a Lilypad
}