package com.mvz;

public class Tile {
    private Integer x;
    private Integer y;
    private String owner;
    private Boolean isAquatic;

    public Tile(Integer x, Integer y, String owner, Boolean isAquatic){
        this.x = x;
        this.y = y;
        this.owner = owner;
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

    public String getO(){
        return owner;
    }

    // tile gabisa berubah 
    public boolean getIsA(){
        return isAquatic;
    }

    public void setX(Integer x){
        this.x = x;
    }

    public void setY(Integer y){
        this.y = y;
    }

    // alternatif dari dissapear, owner = ""
    public void setO(String owner){
        this.owner = owner;
    }

    public void swap(){
        isAquatic  = !isAquatic;
    }


    // buat agar satu tile bisa 
}
