package com.mvz;

import java.util.ArrayList;

public class Tile {
    private Integer x;
    private Integer y;
    private ArrayList<Character> owners; 
    private Boolean isAquatic;

    public Tile(Integer x, Integer y, Boolean isAquatic){
        this.x = x;
        this.y = y;
        owners = new ArrayList<>();
        this.isAquatic = isAquatic;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public int[] getPosition() {
        return new int[]{x, y};
    }

    // tile gabisa berubah 
    public boolean getIsA(){
        return isAquatic;
    }

    // akan di iterasi untuk mengidentifikasi tanaman seperti lilypad atau zombie yang akan menerima dmg
    public ArrayList<Character> getOwners(){
        return owners;
    }

    // zombie atau tanaman baru
    public void addOwner(Character owner){
        this.owners.add(owner);
    }

    // zombie atau tanaman dissapear
    public void removeOwner(Character owner){
        this.owners.remove(owner);
    }

    // dipakai jika kesulitan mengiterasi untuk identifikasi ketiadaan akan sesuatu
    public boolean isOwnersEmpty(){
        return owners.isEmpty();
    }
    
    // setX dan setY tidak akan berubah (kepakai)
    public void setX(Integer x){
        this.x = x;
    }

    public void setY(Integer y){
        this.y = y;
    }

    // method darurat saja
    public void swap(){
        isAquatic  = !isAquatic;
    }

}
