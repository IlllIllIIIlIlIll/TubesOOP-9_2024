package com.mvz.plants;

import java.util.ArrayList;
import java.util.List;
import com.mvz.Plant;

public class Lilypad extends Plant implements PlantComponent{
    private List<PlantComponent> children;

    public Lilypad(Integer x, Integer y) {
        super("Lilypad", 25, 100.0f,  0.0f, 0.0f, 0, 10, true, x, y);
        children = new ArrayList<>();
        
    }

    public Lilypad() {
        super("Lilypad", 25, 100.0f,  0.0f, 0.0f, 0, 10, true);
        children = new ArrayList<>();
    }

    @Override
    public boolean addOnLilypad(PlantComponent plant){
        if(children.isEmpty()){
            if(plant.canBePlacedOnLilyPad()){
                children.add(plant);
                return true;
            }
        }
        return false;
    }
    
    @Override
    public void removeOnLilypad(PlantComponent plant){
        children.remove(plant);
    }

    private float getTotalHealth() {
        float totalHealth = this.health;
        for (PlantComponent plant : children) {
            totalHealth += ((Plant) plant).getHealth();
        }
        return totalHealth;
    }

    @Override
    public void decreaseHealth(Float damage) {
        float totalHealth = getTotalHealth() - damage;

        if (totalHealth <= 0) {
            this.health = 0f;
            for (PlantComponent plant : children) {
                ((Plant) plant).setHealth(0f);
            }
        } else {
            float remainingDamage = damage;
            for (PlantComponent plant : children) {
                float plantHealth = ((Plant) plant).getHealth();
                if (remainingDamage >= plantHealth) {
                    remainingDamage -= plantHealth;
                    ((Plant) plant).setHealth(0f);
                } else {
                    ((Plant) plant).decreaseHealth(remainingDamage);
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
        for(PlantComponent plant : children) {
            if(((Plant) plant).getHealth() > 0){
               ad =  ((Plant) plant).getAD(); 
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
        for(PlantComponent plant : children) {
            if(((Plant) plant).getHealth() > 0){
                range = ((Plant) plant).getRange(); 
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
        for (PlantComponent component : children) {
            if (component instanceof Plant) {
                Plant plant = (Plant) component;
                plants.add(plant);
            }
        }
        return plants;
    }

    public void action(){

    }

}
// attack damage n getrange