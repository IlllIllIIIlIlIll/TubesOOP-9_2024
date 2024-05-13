package com.mvz.plants;

import com.mvz.Plant;
import com.mvz.Tile;
import com.mvz.Zombie;
import com.mvz.Character;

public class Squash extends Plant {
    public Squash(Tile tile) {
        super("Squash", 50, 9999.0f,  5000.0f, 0.0f, 1, 10, false, tile);
    }

    public Squash() {
        super("Squash", 50, 9999.0f,  5000.0f, 0.0f, 1, 10, false);
    }
    
    // baca tile +1 didepan dan belakangnya juga, prioritaskan yang di belakangnya, attack zommbie pada tile tsb
    public void action(){
        Tile currentTile = getTile();
        int currentX = currentTile.getX();
        boolean pass = false;


        // check the current tile
        for (Character owner : currentTile.getOwners()) {
            if (owner instanceof Zombie && ((Zombie) owner).getHealth() > 0) {
                Float damage = this.getAD();
                owner.decreaseHealth(damage);}
            else {pass = true;}}

        if (pass){
            pass = false;
            currentTile.setX(currentX-1);
            for (Character frontOwner : currentTile.getOwners()) {
                if (frontOwner instanceof Zombie && ((Zombie) frontOwner).getHealth() > 0) {
                    Float damage = this.getAD();
                    frontOwner.decreaseHealth(damage);}
            else {pass = true;}}}
        if(pass){
            pass = false;
            currentTile.setX(currentX+1);
            for (Character behindOwner : currentTile.getOwners()){
                if (behindOwner instanceof Zombie && ((Zombie) behindOwner).getHealth() > 0) {
                    Float damage = this.getAD();
                    behindOwner.decreaseHealth(damage);}
                else {pass = true;}}}
        this.setHealth(0.0f);
    }
}


                             
        /* 
        //check the current tile
        if (currentX == 0) {
            currentTile.setX(currentX);
            Tile tileCurrent = getTile();
           
            for (Character owner : tileCurrent.getOwners()) {
                if (owner instanceof Zombie && ((Zombie) owner).getHealth()>0) {
                    Float damage = this.getAD();
                    owner.decreaseHealth(damage);
                }
            }
       }
       //check the tile behind
       if (currentX - 1 >= 0) {
        currentTile.setX(currentX-1);
        Tile tileBehind = getTile();
       
        for (Character owner : tileBehind.getOwners()) {
            if (owner instanceof Zombie && ((Zombie) owner).getHealth()>0) {
                Float damage = this.getAD();
                owner.decreaseHealth(damage);
            }
        }
   }

       if(currentX + 1 < 10){
            currentTile.setX(currentX+1);
            Tile tileBehind = getTile();
        
            for (Character owner : tileBehind.getOwners()) {
                if (owner instanceof Zombie && ((Zombie) owner).getHealth()>0) {
                    Float damage = this.getAD();
                    owner.decreaseHealth(damage);
                }
            }
       }
       */
