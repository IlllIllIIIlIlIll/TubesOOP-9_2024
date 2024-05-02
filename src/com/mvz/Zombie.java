package com.mvz;

public abstract class Zombie extends Character{
    private Float movement_speed;

    public Zombie(String name, Integer health, Float attack_damage, Float attack_speed, Float movement_speed, boolean isAquatic, Tile tile){
        super(name, health, isAquatic, attack_speed, attack_damage, tile);
        this.movement_speed = movement_speed;
    }

    public Float getMS(){
        return movement_speed;
    }

    // untuk snowpea
    public void setMSD(Float movement_speed){
        this.movement_speed = movement_speed;
    }

    // bikin defeat, sudah berada di tile x = 9
    public void attack_home(){

    }
    
}
