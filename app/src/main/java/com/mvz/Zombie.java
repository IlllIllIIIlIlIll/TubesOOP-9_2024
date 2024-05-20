package com.mvz;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public abstract class Zombie extends Character {
    private Float movement_speed;
    private boolean isChilled;
    private boolean canMove;
    private transient ScheduledExecutorService movementExecutorService; 

    // waktu tersisa untuk zombie dapat bergerak
    private long timeRemainingToMove;

    public Zombie(String name, Float health, Float attack_damage, Float attack_speed, Float movement_speed, boolean isAquatic, Integer x, Integer y) {
        super(name, health, isAquatic, attack_speed, attack_damage, x, y);
        this.movement_speed = movement_speed;
        isChilled = false;
        canMove = true;
        timeRemainingToMove = Math.round(movement_speed * 1000);
        initScheduledExecutors();
    }

    // mulai timer dari awal
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

    // buat toggling aja
    public void setCM() {
        canMove = !canMove;
    }

    // canMove yang lebih explisit
    public void setCM(boolean canMove) {
        this.canMove = canMove;
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

    // akan di set true setiap mov.spd sec
    private void startMovementTimer() {
        movementExecutorService.scheduleAtFixedRate(() -> {
            canMove = true;
        }, timeRemainingToMove, Math.round(movement_speed * 1000), TimeUnit.MILLISECONDS);
    }

    // stop timer sekarang, buat timer baru (untuk event khusus)
    public void resetMovementTimer() {
        movementExecutorService.shutdownNow();
        movementExecutorService = Executors.newSingleThreadScheduledExecutor();
        startMovementTimer();
    }

    // mastiin mulai timer dari awal (akan dipanggil)
    public void initZombieScheduledExecutors() {
        initScheduledExecutors();
    }

    public void startAttackTimer() {
        attackExecutorService.scheduleAtFixedRate(() -> {
            canAction = true;
        }, 0, Math.round(attack_speed * 1000), TimeUnit.MILLISECONDS);
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
}
