package com.mvz;

public class Player {
    private String name;
    private Integer sun;

    public Player(String name, Integer sun){
        this.name = name;
        this.sun = sun;
    }

    public String getName(){
        return name;
    }

    public Integer getSun(){
        return sun;
    }

    public void decreaseSun(Integer sun){
        this.sun-=sun;
    }

    public void increaseSun(Integer sun){
        this.sun+=sun;
    }

    public void pauseGame(){

    }

    public void saveGame(){
        
    }
}
