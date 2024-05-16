package com.mvz;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public abstract class Zombie extends Character {
    private Float movement_speed;
    private boolean isChilled;
    private boolean canMove;
    private boolean attackStarted;
    private transient ScheduledExecutorService movementExecutorService; 
    private transient ScheduledExecutorService attackExecutorService; 
    private long timeRemainingToMove;
    private long timeRemainingToAttack;

    public Zombie(String name, Float health, Float attack_damage, Float attack_speed, Float movement_speed, boolean isAquatic, Integer x, Integer y) {
        super(name, health, isAquatic, attack_speed, attack_damage, x, y);
        this.movement_speed = movement_speed;
        isChilled = false;
        canMove = true;
        attackStarted = false;
        timeRemainingToMove = Math.round(movement_speed * 1000);
        timeRemainingToAttack = Math.round(attack_speed * 1000);
        initScheduledExecutors();
    }

    public Zombie() {
        // Default constructor for deserialization
    }

    private void initScheduledExecutors() {
        movementExecutorService = Executors.newSingleThreadScheduledExecutor();
        attackExecutorService = Executors.newSingleThreadScheduledExecutor();
        startMovementTimer();
    }

    public Float getMS() {
        return movement_speed;
    }

    public boolean getCH() {
        return isChilled;
    }

    public boolean getCM() {
        return canMove;
    }

    public void setCH() {
        isChilled = !isChilled;
    }

    public void setCM() {
        canMove = !canMove;
    }

    public void setMSD(Float movement_speed) {
        this.movement_speed = movement_speed;
    }

    public void setATS(Float attack_speed) {
        this.attack_speed = attack_speed;
    }

    public long getTimeRemainingToMove() {
        return timeRemainingToMove;
    }

    public void setTimeRemainingToMove(long timeRemainingToMove) {
        this.timeRemainingToMove = timeRemainingToMove;
    }

    public long getTimeRemainingToAttack() {
        return timeRemainingToAttack;
    }

    public void setTimeRemainingToAttack(long timeRemainingToAttack) {
        this.timeRemainingToAttack = timeRemainingToAttack;
    }

    private void startMovementTimer() {
        movementExecutorService.scheduleAtFixedRate(() -> {
            canMove = true;
        }, timeRemainingToMove, Math.round(movement_speed * 1000), TimeUnit.MILLISECONDS);
    }

    public void startAttackTimer() {
        attackExecutorService.scheduleAtFixedRate(() -> {
            canAction = true;
        }, timeRemainingToAttack, Math.round(attack_speed * 1000), TimeUnit.MILLISECONDS);
    }

    public void resetAttackTimer() {
        attackExecutorService.shutdownNow();
        attackExecutorService = Executors.newSingleThreadScheduledExecutor();
        attackStarted = false;
    }

    public void initiateAttack() {
        if (!attackStarted) {
            startAttackTimer();
            attackStarted = true;
        }
    }

    public void initZombieScheduledExecutors() {
        initScheduledExecutors();
    }
}
