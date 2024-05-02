package com.mvz;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import com.mvz.zombies.Conehead;
import com.mvz.zombies.Normal;

public class Game {
    private Map map;
    private Timer timer;
    private Random random;
    private Tile[][] tile;
    private Boolean isDaytime;
    private Player player;
    private Thread sunThread;
    private Thread zombieThread;

    // Displays inventory (plant + cost)

    // Player pick plants in deck

    // GAME STARTS

    // Generate map
        public Game(Player player) {
            this.player = player;
            this.map = new Map();
            isDaytime = true;
        }

    // new thread for sun generating
    // changing daytime/nighttime for every 100 seconds
    // if its daytime, generate sun 25 every (5-10) seconds call increaseSun() method from Player class
    public void generateSun() {
        sunThread = new Thread(() -> {
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    // Toggle between daytime and nighttime
                    isDaytime = !isDaytime;

                    // If it's daytime, generate sun 25 every (5-10) seconds
                    if (isDaytime) {
                        int delay = 5000 + random.nextInt(5000); // Random delay between 5 and 10 seconds
                        new Timer().schedule(new TimerTask() {
                            @Override
                            public void run() {
                                player.increaseSun(25);
                            }
                        }, delay);
                    }
                }
            }, 0, 100000); // Start immediately and run every 100 seconds
        });
        sunThread.start();
    }


    // new thread for zombie spawning
    // thread below this
    // tiap detik terupdate per masing2 jalur (semangat ya)
    public void startSpawningZombies() {
        zombieThread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000); // Pause for 1 second
                } catch (InterruptedException e) {
                    // Handle exception
                }
    
                setPosition(); // Assuming this method is implemented
    
                for (int i = 0; i < tile[0].length; i++) { // assuming tile[0].length gives the number of rows (y-axis)
                    if (random.nextFloat() < 0.3) {
                        // Create a new Zombie object here and place it on the map
                        Zombie z;
                        if (random.nextBoolean()) {
                            z = new Normal(tile[9][i]); // spawn at x=9
                        } else {
                            z = new Conehead(tile[9][i]); // spawn at x=9
                        }
                        placeZombie(z);
                    }
                }
            }
        });
        zombieThread.start();
    }
    
    // moves zombies +1 tile
    public void setPosition(){

    }

    public void placeZombie(Zombie z){
        // Add the zombie to the list of zombies on the map

        // You would also need to add code here to place the zombie on a specific tile
    }

    public void winGame(){
        // Code to handle winning the game
        System.out.println("Congratulations, you've won the game!");
    }

    public void loseGame(){
        // Code to handle losing the game
        System.out.println("Sorry, you've lost the game.");
    }
}
