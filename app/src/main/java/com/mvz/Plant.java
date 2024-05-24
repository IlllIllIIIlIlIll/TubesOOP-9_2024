package com.mvz;

// Abstract class representing a plant in the game, extending the Character class
public abstract class Plant extends Character {
    private int cost;       // Cost of the plant
    private int range;      // Attack range of the plant
    private int cooldown;   // Cooldown of the plant

    // Constructor with parameters including position coordinates
    public Plant(String name, int cost, Float health, Float attack_damage, Float attack_speed, int range, int cooldown, boolean isAquatic, Integer x, Integer y) {
        super(name, health, isAquatic, attack_speed, attack_damage, x, y);
        this.cost = cost;
        this.range = range;
        this.cooldown = cooldown;
    }

    // Constructor with parameters excluding position coordinates
    public Plant(String name, int cost, Float health, Float attack_damage, Float attack_speed, int range, int cooldown, boolean isAquatic) {
        super(name, health, isAquatic, attack_speed, attack_damage);
        this.cost = cost;
        this.range = range;
        this.cooldown = cooldown;
    }

    // Default constructor for deserialization
    public Plant() {
        
    }

    // Getter for the cost of the plant
    public int getCost(){
        return cost;
    }

    // Getter for the attack range of the plant
    public int getRange(){
        return range;
    }

    // Getter for the cooldown of the plant
    public int getCD(){
        return cooldown;
    }
    
    // Method to check if the player has enough sun to buy the plant
    public boolean canBuyThePlant() {
        return Sun.getSun() >= cost;
    }

    // Abstract method to check if the plant is ready to be planted based on cooldown
    public abstract boolean isReadyToBePlanted();

    // Abstract method to set the last planted time to the current time after planting the plant
    public abstract void setLastPlantedTime(long time);

}