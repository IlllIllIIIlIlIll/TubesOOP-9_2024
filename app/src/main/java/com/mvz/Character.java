package com.mvz;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

// Abstract class representing a game character with common properties and behaviors
public abstract class Character implements Action {
    protected String name;
    protected Float health;
    protected boolean isAquatic;
    protected Float attack_speed;
    protected Float attack_damage;
    protected boolean canAttack;
    protected int x;
    protected int y;

    // Executor service for scheduling tasks
    private transient ScheduledExecutorService attackExecutorService; 

    // Time remaining for the next attack
    private long timeRemainingToAttack;

    // Indicates if the attack timer has started
    private boolean attackStarted;

    // Constructor initializing character properties and starting the attack timer if applicable
    public Character(String name, Float health, boolean isAquatic, Float attack_speed, Float attack_damage, Integer x, Integer y) {
        this.x = x;
        this.y = y;
        this.health = health;
        this.name = name;
        this.isAquatic = isAquatic;
        this.attack_speed = attack_speed;
        this.attack_damage = attack_damage;
        this.canAttack = true;
        this.timeRemainingToAttack = Math.round(attack_speed * 1000);
        this.attackStarted = false;

        // Start attack timer if attack speed is greater than 0
        if (attack_speed > 0) {
            this.attackExecutorService = Executors.newSingleThreadScheduledExecutor();
            startAttackTimer();
        }
    }

    // Constructor for character without specified position
    public Character(String name, Float health, boolean isAquatic, Float attack_speed, Float attack_damage) {
        this.health = health;
        this.name = name;
        this.isAquatic = isAquatic;
        this.attack_speed = attack_speed;
        this.attack_damage = attack_damage;
        this.canAttack = true;
    }

    // Default constructor for deserialization
    public Character() {
       
    }

    public Integer getXChar() {
        return x;
    }

    public Integer getYChar() {
        return y;
    }

    public void setXChar(Integer x) {
        this.x = x;
    }

    public void setYChar(Integer y) {
        this.y = y;
    }

    public Float getHealth() {
        return health;
    }

    public void setHealth(Float health) {
        this.health = health;
    }

    public void decreaseHealth(Float damage) {
        this.health -= damage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAquatic() {
        return isAquatic;
    }

    public void setAquatic(boolean isAquatic) {
        this.isAquatic = isAquatic;
    }

    public Float getAS() {
        return attack_speed;
    }

    public void setAS(Float attack_speed) {
        this.attack_speed = attack_speed;
    }

    public Float getAD() {
        return attack_damage;
    }

    public void setAttack_damage(Float attack_damage) {
        this.attack_damage = attack_damage;
    }

    public boolean getcanAttack() {
        return canAttack;
    }

    public void setcanAttack(boolean canAttack) {
        this.canAttack = canAttack;
    }

    // Getter and setter for timeRemainingToAttack
    public long getTimeRemainingToAttack() {
        return timeRemainingToAttack;
    }

    public void setTimeRemainingToAttack(long timeRemainingToAttack) {
        this.timeRemainingToAttack = timeRemainingToAttack;
    }

    // Sets canAttack to true at intervals based on attack speed
    public void startAttackTimer() {
        attackExecutorService.scheduleAtFixedRate(() -> {
            canAttack = true;
        }, timeRemainingToAttack, Math.round(attack_speed * 1000), TimeUnit.MILLISECONDS);
    }
    
    // Stops the current timer and creates a new one (e.g., when the character moves)
    public void resetAttackTimer() {
        attackExecutorService.shutdownNow();
        attackExecutorService = Executors.newSingleThreadScheduledExecutor();
        attackStarted = false;
    }

    // Ensures the attack timer starts if it hasn't already
    public void initiateAttack() {
        if (!attackStarted) {
            startAttackTimer();
            attackStarted = true;
        }
    }

    // Initializes the scheduled executor service for the character
    public void initScheduledExecutorService() {
        if (attack_speed > 0) {
            this.attackExecutorService = Executors.newSingleThreadScheduledExecutor();
            startAttackTimer();
        }
    }
    
    // Abstract method to define character-specific actions
    public abstract void action();
}
