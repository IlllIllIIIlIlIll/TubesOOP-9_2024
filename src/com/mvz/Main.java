package com.mvz;

public class Main {
    public static void main(String[] args) {

        Player favian = new Player("Favian", 0);
        
        Game game = new Game(favian);

        game.generateSun();
        game.startSpawningZombies();
    }
}