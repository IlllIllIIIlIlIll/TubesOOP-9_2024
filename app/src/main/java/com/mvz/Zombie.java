package com.mvz;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public abstract class Zombie extends Character {
    private Float movement_speed;
    private boolean isChilled;
    private boolean canMove;
    private transient ScheduledExecutorService movementExecutorService; 
    private long timeRemainingToMove;

    public Zombie(String name, Float health, Float attack_damage, Float attack_speed, Float movement_speed, boolean isAquatic, Integer x, Integer y) {
        super(name, health, isAquatic, attack_speed, attack_damage, x, y);
        this.movement_speed = movement_speed;
        isChilled = false;
        canMove = true;
        timeRemainingToMove = Math.round(movement_speed * 1000);
        initScheduledExecutors();
    }

    private void initScheduledExecutors() {
        movementExecutorService = Executors.newSingleThreadScheduledExecutor();
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

    public long getTimeRemainingToMove() {
        return timeRemainingToMove;
    }

    public void setTimeRemainingToMove(long timeRemainingToMove) {
        this.timeRemainingToMove = timeRemainingToMove;
    }

    private void startMovementTimer() {
        movementExecutorService.scheduleAtFixedRate(() -> {
            canMove = true;
        }, timeRemainingToMove, Math.round(movement_speed * 1000), TimeUnit.MILLISECONDS);
    }

    public void resetMovementTimer() {
        movementExecutorService.shutdownNow();
        movementExecutorService = Executors.newSingleThreadScheduledExecutor();
        startMovementTimer();
    }

    public void initZombieScheduledExecutors() {
        initScheduledExecutors();
    }
}
