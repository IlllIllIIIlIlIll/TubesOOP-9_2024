package com.mvz;

public abstract class Zombie extends Character implements Attack{
    private Integer movement_speed;

    public Zombie(String name, Integer health, boolean isAquatic, Float attack_speed, Float attack_damage, Integer movement_speed, Tile tile){
        super(name, health, isAquatic, attack_speed, attack_damage, tile);
        this.movement_speed = movement_speed;
    }

    public Integer getMS(){
        return movement_speed;
    }

    // untuk snowpea
    public void setMSD(Integer movement_speed){
        this.movement_speed = movement_speed;
    }

    // bikin defeat, sudah berada di tile x = 9
    public void attack_home(){

    }

    // mengurangi health tanaman
    public void attack(){

    }
    
}
