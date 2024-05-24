package com.mvz.plants;

import java.util.ArrayList;
import java.util.List;
import com.mvz.Plant;

// Represents a Lilypad plant in the game, capable of holding other plants as components
public class Lilypad extends Plant implements PlantComponent{
    // Static variable to track the last time a Lilypad was planted
    public static long lastPlantedTime;
    // List to hold child plants attached to the Lilypad
    private List<Plant> children;

    // Constructor for creating a Lilypad plant at a specific position
    public Lilypad(Integer x, Integer y) {
        super("Lilypad", 25, 100.0f,  0.0f, 0.0f, 0, 10, true, x, y);
        children = new ArrayList<>();   // Initialize the list of child plants
        
    }

    // Default constructor for creating a Lilypad plant without specifying a position
    public Lilypad() {
        super("Lilypad", 25, 100.0f,  0.0f, 0.0f, 0, 10, true);
        children = new ArrayList<>();   // Initialize the list of child plants
    }

    @Override
    // Method to add a plant to the Lilypad, checking if it can be placed
    public boolean addOnLilypad(Plant plant){
        if(children.isEmpty()){
            if(canBePlacedOnLilyPad()){
                children.add(plant);
                return true;
            }
        }
        return false;
    }
    
    @Override
    // Method to remove a plant from the Lilypad
    public void removeOnLilypad(Plant plant){
        children.remove(plant);
    }

    // Method to calculate the total health of the Lilypad and its children
    private float getTotalHealth() {
        float totalHealth = this.health;
        for (Plant plant : children) {
            totalHealth += plant.getHealth();
        }
        return totalHealth;
    }

    @Override
    // Override the decreaseHealth method to apply damage to both the Lilypad and its children
    public void decreaseHealth(Float damage) {
        float totalHealth = getTotalHealth() - damage;

        if (totalHealth <= 0) {
            this.health = 0f;
            for (Plant plant : children) {
                plant.setHealth(0f);
            }
        } else {
            float remainingDamage = damage;
            for (Plant plant : children) {
                float plantHealth = plant.getHealth();
                if (remainingDamage >= plantHealth) {
                    remainingDamage -= plantHealth;
                    plant.setHealth(0f);
                } else {
                    plant.decreaseHealth(remainingDamage);
                    remainingDamage = 0f;
                }
            }
            this.health -= remainingDamage;
        }
    }

   @Override 
   // Method to check if the Lilypad can have another plant added to it
    public boolean canBePlacedOnLilyPad(){
        return children.isEmpty();
    }
    

    @Override
    // Override the getAD method to return the AD of the first healthy child plant
    public Float getAD() {
        Float ad = 0.0f;
        for(Plant plant : children) {
            if(plant.getHealth() > 0){
               ad =  plant.getAD(); 
            }
            else{    
                ad = this.getAD();
                break;
            }
        }
        return ad;
    }

    @Override
    // Override the getRange method to return the range of the first healthy child plant
    public int getRange(){
        int range=0;
        for(Plant plant : children) {
            if(plant.getHealth() > 0){
                range = plant.getRange(); 
            }
            else{    
                range = this.getRange();
                break;
            }
        }
        return range;
    }

    // Method to retrieve a list of all plants currently attached to the Lilypad
    public List<Plant> getPlants() {
        List<Plant> plants = new ArrayList<>();
        for (Plant component : children) {
            if (component instanceof Plant) {
                Plant plant = component;
                plants.add(plant);
            }
        }
        return plants;
    }

    // Methods to manage planting times and actions similar to other plant classes
    public boolean isReadyToBePlanted() {
        long currentTime = System.currentTimeMillis();
        long elapsedTime = currentTime - lastPlantedTime;
        return elapsedTime >= getCD();
    }

    public void setLastPlantedTime(long time) {
        if (time > lastPlantedTime) lastPlantedTime = time;
    }
    
    // Placeholder for the action method, intended to be overridden by subclasses
    public void action(){

    }

}