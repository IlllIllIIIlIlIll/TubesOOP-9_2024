package com.mvz;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import com.mvz.zombies.*;


public class Game {
    private Map map;
    private Timer timer;
    private Random random;
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
        this.timer = new Timer();
        this.random = new Random();
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
                                Sun.increaseSun(25);
                            }
                        }, delay);
                    }
                }
            }, 0, 100000); // Start immediately and run every 100 seconds
        });
        sunThread.start();
    }


    // new thread for zombie spawning, after 20 seconds
    // thread below this
    // tiap detik terupdate per masing2 jalur (semangat ya)
    public void startSpawningZombies() {
        ZombieFactory landFactory = new LandZombieFactory();
        ZombieFactory waterFactory = new WaterZombieFactory();
        zombieThread = new Thread(() -> {
            map.printMap();
            while (true) {
                try {
                    Thread.sleep(1000); // Pause for 1 second
                } catch (InterruptedException e) {
                    // Handle exception
                }
    
                map.setPosition(); 
    
                for (int i = 0; i < 6; i++) {
                    if (random.nextFloat() < 0.3) {
                        // Create a new Zombie object here and place it on the map
                        Zombie z;
                        Tile tile = map.getTile(10, i);
                        if (tile != null) {
                            ZombieFactory factory;
                            String[] types;
                            if (i == 2 || i == 3) {
                                factory = waterFactory;
                                types = waterFactory.getTypes();
                            } else {
                                factory = landFactory;
                                types = landFactory.getTypes();
                            }
                            int typeIndex = random.nextInt(types.length);
                            z = factory.createZombie(types[typeIndex], tile);
                            map.placeZombie(z, i);
                        }
                    }
                }
                // print 
                map.printMap();
            }
        });
        zombieThread.start();
    }
    
}
