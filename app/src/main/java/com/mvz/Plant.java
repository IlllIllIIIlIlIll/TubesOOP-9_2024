package com.mvz;

public abstract class Plant extends Character {
    private int cost;
    private int range;
    private int cooldown;

    public Plant(String name, int cost, Float health, Float attack_damage, Float attack_speed, int range, int cooldown, boolean isAquatic, Integer x, Integer y) {
        super(name, health, isAquatic, attack_speed, attack_damage, x, y);
        this.cost = cost;
        this.range = range;
        this.cooldown = cooldown;
    }

    public Plant(String name, int cost, Float health, Float attack_damage, Float attack_speed, int range, int cooldown, boolean isAquatic) {
        super(name, health, isAquatic, attack_speed, attack_damage);
        this.cost = cost;
        this.range = range;
        this.cooldown = cooldown;
    }

    // indikator pertama player untuk dapat membeli tanaman
    public int getCost(){
        return cost;
    }

    // plant attacks nearest zombie that has greater x value
    public int getRange(){
        return range;
    }

    // indikator kedua player untuk dapat membeli tanaman
    public int getCD(){
        return cooldown;
    }

    //liat sun si player cukup ga buat beli
    public boolean canBuyThePlant() {
        return Sun.getSun() >= cost;
    }

    // cek berdasarkan cooldown
    public abstract boolean isReadyToBePlanted();

    // set lastPlantedTime jadi time terbaru setelah plant berhasil ditanam
    public abstract void setLastPlantedTime(long time);
}