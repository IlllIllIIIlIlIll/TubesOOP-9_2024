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
    private Thread sunThread;
    private Thread zombieThread;
    private boolean isPaused = false;

    public Map getMap(){
        return map;
    }

    public void setMap(Map map){
        this.map = map;
    }
    // GAME STARTS

    // Generate map
    public Game() {
        this.map = new Map();
        this.timer = new Timer();
        this.random = new Random();
        isDaytime = true;
    }

    // new thread for sun generating
    // Method to start generating sun based on the game's day cycle
    public void generateSun() {
        sunThread = new Thread(() -> {
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    toggleDayNight();

                    if (isDaytime) {
                        generateSunPeriodically();
                    }
                }
            }, 0, 100000); // Start immediately and run every 100 seconds
        });
        sunThread.start();
    }

    private void toggleDayNight() {
        isDaytime = !isDaytime; // Toggle daytime and nighttime
    }

    private void generateSunPeriodically() {
        int delay = 5000 + random.nextInt(5000); // Random delay between 5 and 10 seconds
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                increaseSun(25); // Increase sun points by 25
            }
        }, delay);
    }

    public void increaseSun(int amount) {
        Sun.increaseSun(amount);
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
    

    public synchronized void pauseGame() {
        isPaused = true;
        // Handling for stopping threads or pausing their execution
        // This could be more complex depending on how threads are managed
    }

    public synchronized void resumeGame() {
        isPaused = false;
        // Handling for resuming threads or starting them if not alive
        if (!sunThread.isAlive()) {
            generateSun();
        }
        if (!zombieThread.isAlive()) {
            startSpawningZombies();
        }
    }

    public boolean isPaused() {
        return isPaused;
    }

}
