package com.mvz;

public abstract class Plant extends Character implements Attack {
    private int cost;
    private int range;
    private int cooldown;
    private long lastPlantedTime;

    public Plant(String name, Integer health, boolean isAquatic, Float attack_speed, Float attack_damage, int cost, int range, int cooldown, Tile tile) {
        super(name, health, isAquatic, attack_speed, attack_damage, tile);
        this.cost = cost;
        this.range = range;
        this.cooldown = cooldown;
        this.lastPlantedTime = System.currentTimeMillis();
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

    /*
     cek sun
     cek cooldown
     */
    public boolean canPlant() {
        long currentTime = System.currentTimeMillis();
        long elapsedTime = currentTime - lastPlantedTime;
        return elapsedTime >= cooldown;
    }

    // user menaruh tanaman, masukan lokasi x dan y
    /*
     throw exception jika sun kurang, masih cooldown, tile sudah punya owner (occupied), koordinat input tile invalid (melebihi 9x6 atau isAquatic)

     */
    public void scatter() {
        if (canPlant()) {
            lastPlantedTime = System.currentTimeMillis();
            System.out.println(name + " has been planted!");
        } else {
            System.out.println(name + " is on cooldown!");
        }
    }
}