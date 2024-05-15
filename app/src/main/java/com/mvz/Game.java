package com.mvz;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import com.mvz.zombies.*;

public class Game {
    private Player player;
    private Map map;
    private Timer timer;
    private Random random;
    private Boolean isDaytime;
    private Thread sunThread;
    private Thread zombieThread;
    private boolean isPaused = false;
    private long elapsedTime = 0;
    private long startTime = 0;

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    // Generate map
    public Game(Player player) {
        this.player = player;
        this.map = new Map();
        this.timer = new Timer();
        this.random = new Random();
        isDaytime = true;
    }

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
            }, 0, 100000);
        });
        sunThread.start();
    }

    private void toggleDayNight() {
        isDaytime = !isDaytime;
    }

    private void generateSunPeriodically() {
        int delay = 5000 + random.nextInt(5000);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                if (getElapsedTime() < 100000) {
                    increaseSun(25);
                }
            }
        }, delay);
    }

    public void increaseSun(int amount) {
        Sun.increaseSun(amount);
    }

    public void startSpawningZombies() {
        ZombieFactory landFactory = new LandZombieFactory();
        ZombieFactory waterFactory = new WaterZombieFactory();
        zombieThread = new Thread(() -> {
            while (true) {
                synchronized (Game.this) {
                    while (isPaused) {
                        try {
                            Game.this.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

                for (int i = 0; i < 6; i++) {
                    if (random.nextFloat() < 0.3) {
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
            }
        });
        zombieThread.start();
    }

    public void startGame() {
        startTime = System.currentTimeMillis();
    }

    public synchronized void pauseGame() {
        isPaused = true;
        elapsedTime += System.currentTimeMillis() - startTime;
    }

    public synchronized void resumeGame() {
        isPaused = false;
        startTime = System.currentTimeMillis();
        notifyAll();
    }

    public long getElapsedTime() {
        if (isPaused) {
            return elapsedTime;
        } else {
            return elapsedTime + System.currentTimeMillis() - startTime;
        }
    }

    public synchronized boolean isPaused() {
        return isPaused;
    }
}
