package com;

import com.mvz.*;
import com.mvz.menu.StartMenu;

public class Main {
    public static void main(String[] args) {
        Player favian = new Player("Favian");
        Menu menu = new StartMenu(favian); 
        menu.displayMenu();
    }
}

// public class Main {
//     public static void main(String[] args) {

//         Player favian = new Player("Favian");
        
//         Game game = new Game(favian);

//         game.generateSun();
//         game.startSpawningZombies();
//     }
// }
