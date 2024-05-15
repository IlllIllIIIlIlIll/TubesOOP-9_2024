package com.mvz;

import java.util.concurrent.*;

public abstract class Zombie extends Character {
    private Float movement_speed;
    private boolean isChilled;
    private boolean canMove;
    private ScheduledExecutorService executorService;

    public Zombie(String name, Float health, Float attack_damage, Float attack_speed, Float movement_speed, boolean isAquatic, Integer x, Integer y) {
        super(name, health, isAquatic, attack_speed, attack_damage, x, y);
        this.movement_speed = movement_speed;
        isChilled = false;
        canMove = true;
        executorService = Executors.newSingleThreadScheduledExecutor();
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

    private void startMovementTimer() {
        executorService.scheduleAtFixedRate(() -> {
            canMove = true;
        }, 0, Math.round(movement_speed * 1000), TimeUnit.MILLISECONDS);
    }
}
