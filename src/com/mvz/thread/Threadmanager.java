package com.mvz.thread;

import com.mvz.Game;

public class Threadmanager {
    private Game game;
    private Thread mainThread;
    private Thread zombieSpawningThread;
    private Thread sunGeneratingThread;

    public Threadmanager(Game game) {
        this.game = game;
    }

    public void startThreads() {
        // Main game thread
        mainThread = new Thread(() -> {
            // Your main game logic here
        });
        mainThread.start();

        // Zombie spawning thread
        zombieSpawningThread = new Thread(() -> {
            game.startSpawningZombies();
        });
        zombieSpawningThread.start();

        // Sun generating thread
        sunGeneratingThread = new Thread(() -> {
            game.generateSun();
        });
        sunGeneratingThread.start();
    }

    public void stopThreads() {
        // Interrupt the threads
        mainThread.interrupt();
        zombieSpawningThread.interrupt();
        sunGeneratingThread.interrupt();
    }
}

