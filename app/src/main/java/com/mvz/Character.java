package com.mvz;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public abstract class Character implements Action {
    protected String name;
    protected Float health;
    protected boolean isAquatic;
    protected Float attack_speed;
    protected Float attack_damage;
    protected boolean canAttack;
    protected int x;
    protected int y;

    // untuk eksekusi "task" dengan scheduling
    private transient ScheduledExecutorService attackExecutorService; 

    // waktu tersisa untuk melakukan attack
    private long timeRemainingToAttack;

    // menandakan timer attack dimulai
    private boolean attackStarted;

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

        // memastikan hanya plant dengan atk spd > 0 dapat menyerang
        if (attack_speed > 0) {
            this.attackExecutorService = Executors.newSingleThreadScheduledExecutor();
            startAttackTimer();
        }
    }

    public Character(String name, Float health, boolean isAquatic, Float attack_speed, Float attack_damage) {
        this.health = health;
        this.name = name;
        this.isAquatic = isAquatic;
        this.attack_speed = attack_speed;
        this.attack_damage = attack_damage;
        this.canAttack = true;
    }

    public Character() {
        // Default constructor for deserialization
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

    // canAttack = true setiap interval atk spd.
    public void startAttackTimer() {
        attackExecutorService.scheduleAtFixedRate(() -> {
            canAttack = true;
        }, timeRemainingToAttack, Math.round(attack_speed * 1000), TimeUnit.MILLISECONDS);
    }
    
    // stop timer sekarang, buat timer baru (saat zombie bergerak)
    public void resetAttackTimer() {
        attackExecutorService.shutdownNow();
        attackExecutorService = Executors.newSingleThreadScheduledExecutor();
        attackStarted = false;
    }

    // memastikan attack timer dimulai jika belum
    public void initiateAttack() {
        if (!attackStarted) {
            startAttackTimer();
            attackStarted = true;
        }
    }

    // memulai schedule executor
    public void initScheduledExecutorService() {
        if (attack_speed > 0) {
            this.attackExecutorService = Executors.newSingleThreadScheduledExecutor();
            startAttackTimer();
        }
    }

    public abstract void action();
}
