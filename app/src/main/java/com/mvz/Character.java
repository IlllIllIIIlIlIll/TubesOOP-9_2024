package com.mvz;

import java.util.concurrent.*;

public abstract class Character implements Action {
    protected String name;
    protected Float health;
    protected boolean isAquatic;
    protected Float attack_speed;
    protected Float attack_damage;
    protected boolean canAction;
    protected int x;
    protected int y;
    private ScheduledExecutorService executorService;

    public Character(String name, Float health, boolean isAquatic, Float attack_speed, Float attack_damage, Integer x, Integer y) {
        this.x = x;
        this.y = y;
        this.health = health;
        this.name = name;
        this.isAquatic = isAquatic;
        this.attack_speed = attack_speed;
        this.attack_damage = attack_damage;
        this.canAction = true;
        if (attack_speed > 0) {
            this.executorService = Executors.newSingleThreadScheduledExecutor();
            startActionTimer();
        }
    }

    public Character(String name, Float health, boolean isAquatic, Float attack_speed, Float attack_damage) {
        this.health = health;
        this.name = name;
        this.isAquatic = isAquatic;
        this.attack_speed = attack_speed;
        this.attack_damage = attack_damage;
        this.canAction = true;
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

    public void setAttack_speed(Float attack_speed) {
        this.attack_speed = attack_speed;
    }

    public Float getAD() {
        return attack_damage;
    }

    public void setAttack_damage(Float attack_damage) {
        this.attack_damage = attack_damage;
    }

    public boolean getCanAction() {
        return canAction;
    }

    public void setCanAction(boolean canAction) {
        this.canAction = canAction;
    }

    private void startActionTimer() {
        if (attack_speed > 0) {
            executorService.scheduleAtFixedRate(() -> {
                canAction = true;
            }, 0, Math.round(attack_speed * 1000), TimeUnit.MILLISECONDS);
        }
    }

    public void initScheduledExecutorService() {
        if (attack_speed > 0) {
            this.executorService = Executors.newSingleThreadScheduledExecutor();
            startActionTimer();
        }
    }

    public abstract void action();
}
