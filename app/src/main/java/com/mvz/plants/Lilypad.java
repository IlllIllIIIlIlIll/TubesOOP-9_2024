package com.mvz.plants;

import java.util.ArrayList;
import java.util.List;
import com.mvz.Plant;

public class Lilypad extends Plant implements PlantComponent{
    public static long lastPlantedTime;
    private List<Plant> children;

    public Lilypad(Integer x, Integer y) {
        super("Lilypad", 25, 100.0f,  0.0f, 0.0f, 0, 10, true, x, y);
        children = new ArrayList<>();
        
    }

    public Lilypad() {
        super("Lilypad", 25, 100.0f,  0.0f, 0.0f, 0, 10, true);
        children = new ArrayList<>();
    }

    @Override
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
    public void removeOnLilypad(Plant plant){
        children.remove(plant);
    }

    private float getTotalHealth() {
        float totalHealth = this.health;
        for (Plant plant : children) {
            totalHealth += plant.getHealth();
        }
        return totalHealth;
    }

    @Override
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
    public boolean canBePlacedOnLilyPad(){
        return children.isEmpty();
    }
    

    @Override
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

    public boolean isReadyToBePlanted() {
        long currentTime = System.currentTimeMillis();
        long elapsedTime = currentTime - lastPlantedTime;
        return elapsedTime >= getCD();
    }

    public void setLastPlantedTime(long time) {
        if (time > lastPlantedTime) lastPlantedTime = time;
    }
    
    public void action(){

    }

}
// attack damage n getrange