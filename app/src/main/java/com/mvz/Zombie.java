package com.mvz;

import java.util.concurrent.*;

public abstract class Zombie extends Character{
    private Float movement_speed;
    private boolean isChilled;
    private boolean canMove;
    private ScheduledExecutorService executorService;


    public Zombie(String name, Float health, Float attack_damage, Float attack_speed, Float movement_speed, boolean isAquatic, Tile tile){
        super(name, health, isAquatic, attack_speed, attack_damage, tile);
        this.movement_speed = movement_speed;
        isChilled = false;
        canMove = true;
        executorService = Executors.newSingleThreadScheduledExecutor();
    }

    public Float getMS(){
        return movement_speed;
    }

    public Float getAS() {
        return attack_speed;
    }

    public boolean getCH(){
        return isChilled;
    }

    public boolean getCM(){
        return canMove;
    }

    public void setCH(){
        isChilled = !isChilled;
    }

    public void setCM(){
        canMove = !canMove;
    }

    // untuk snowpea
    public void setMSD(Float movement_speed){
        this.movement_speed = movement_speed;
    }

    public void setATS(Float attack_speed){
        this.attack_speed = attack_speed;
    }

    // Method to apply the SnowPea effect
    public void applySnowPeaEffect() {
        if (!isChilled) {
            isChilled = true;
            setMSD(getMS() * 2); // Slow down movement speed
            setATS(getAS() * 2); // Slow down attack speed

            // Schedule a task to reset the effect after 3 seconds
            executorService.schedule(this::resetSnowPeaEffect, 3, TimeUnit.SECONDS);
        }
    }

    // Method to reset the SnowPea effect
    private void resetSnowPeaEffect() {
        if (isChilled) {
            isChilled = false;
            setMSD(getMS() / 2); // Reset movement speed
            setATS(getAS() / 2); // Reset attack speed
        }
    }

    public void move() {
        if (isChilled) {
            canMove = !canMove;
        } else {
            canMove = true;
        }
    }

    
}
