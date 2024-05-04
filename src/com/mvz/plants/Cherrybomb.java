package com.mvz.plants;

import java.util.ArrayList;
import java.util.List;

import com.mvz.Plant;
import com.mvz.Tile;
import com.mvz.Zombie;
import com.mvz.Character;

public class Cherrybomb extends Plant {
    public Cherrybomb(Tile tile) {
        super("Cherry bomb", 150, 9999.0f,  1800.0f, 0.0f, 9, 30, false, tile);
    }

    // immediately attack zombies within the tile and surrounding +1 tile (3x3)
    public void action(){
        Tile currentTile = this.getTile();

        List<Tile> affectedTiles = getSurroundingTiles(currentTile);

        for(Tile tile : affectedTiles){
            for (Character owner : tile.getOwners()) {
                if (owner instanceof Zombie && ((Zombie) owner).getHealth() > 0) {
                    // Attacking the zombie
                    Float damage = this.getAD();
                    ((Zombie) owner).setHealth(damage);
                }
            }
    }
}

    // helper method to get surrounding tiles
    private List<Tile> getSurroundingTiles(Tile center){
        List<Tile> surroundingTiles = new ArrayList<>();
        int centerX = center.getX();
        int centerY = center.getY();

        for(int xChange = -1; xChange <= 1; xChange++ ){
            for(int yChange = -1; yChange <= 1; yChange++){
                int x = centerX + xChange;
                int y = centerY + yChange;
                if (x >= 0 && y >= 0) { 
                     Tile tile = new Tile(x, y, isAquatic); 
                     surroundingTiles.add(tile);
               }
            }
        }
        return surroundingTiles;
    }
}
