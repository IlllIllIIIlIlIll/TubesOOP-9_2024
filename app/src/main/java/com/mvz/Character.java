package com.mvz;

public abstract class Character implements Action{
    // deklarasi variabel
    protected String name;
    protected Float health;
    protected boolean isAquatic;
    protected Float attack_speed;   // float karena bisa didiskon 50% oleh snowpea
    protected Float attack_damage;
    protected Tile tile;

    // konstruktor
    public Character(String name, Float health, boolean isAquatic, Float attack_speed, Float attack_damage, Tile tile){
        this.tile = tile;
        this.health = health;
        this.name = name;
        this.isAquatic = isAquatic;
        this.attack_speed = attack_speed;
        this.attack_damage = attack_damage;
    }

    // mengetahui koordinat plant maupun zombie
    public Tile getTile() {
        return tile;
    }

    // majuin zombie
    public void setTile(Tile tile) {
        this.tile = tile;
    }

    // jika health abis, dissapear
    public Float getHealth() {
        return health;
    }

    // hanya bisa mengurangi health, else exception
    public void setHealth(Float health) {
        this.health = health;
    }

    //mengurangi health jika terkena damage
    public void decreaseHealth(Float damage){
        this.health -= damage;
    }

    // keperluan print
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // harus sama dengan tile yang diinjak
    public boolean isAquatic() {
        return isAquatic;
    }


    public void setAquatic(boolean isAquatic) {
        this.isAquatic = isAquatic;
    }

    // kalkulasi action()
    public Float getAS() {
        return attack_speed;
    }

    // snowpea issue
    public void setAttack_speed(Float attack_speed) {
        this.attack_speed = attack_speed;
    }

    // kalkulasi action()
    public Float getAD() {
        return attack_damage;
    }

    public void setAttack_damage(Float attack_damage) {
        this.attack_damage = attack_damage;
    }

    // bisa berbeda antar tipe tanaman
    public abstract void action();
}
