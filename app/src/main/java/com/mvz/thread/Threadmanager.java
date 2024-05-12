package com.mvz.thread;

import com.mvz.Game;

public class ThreadManager {
    private Game game;
    private Thread mainThread;
    private Thread zombieSpawningThread;
    private Thread sunGeneratingThread;

    public ThreadManager(Game game) {
        this.game = game;
    }

    public void startThreads() {
        // Main game thread
        mainThread = new Thread(() -> {
            // Placeholder for main game logic
        });
        mainThread.start();

        // Zombie spawning thread
        zombieSpawningThread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                if (!game.isPaused()) {
                    game.startSpawningZombies();
                }
                try {
                    Thread.sleep(1000); // Sleep to reduce CPU usage while paused
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); // Ensure the thread's interrupted status is set
                }
            }
        });
        zombieSpawningThread.start();

        // Sun generating thread
        sunGeneratingThread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                if (!game.isPaused()) {
                    game.generateSun();
                }
                try {
                    Thread.sleep(1000); // Sleep to reduce CPU usage while paused
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); // Ensure the thread's interrupted status is set
                }
            }
        });
        sunGeneratingThread.start();
    }

    public void pauseThreads() {
        // Set game state to paused, actual thread pausing handled in the threads themselves
        game.pauseGame();
    }

    public void resumeThreads() {
        // Resume game logic
        game.resumeGame();
    }

    public void stopThreads() {
        // Interrupt the threads to stop their execution
        if (mainThread != null) mainThread.interrupt();
        if (zombieSpawningThread != null) zombieSpawningThread.interrupt();
        if (sunGeneratingThread != null) sunGeneratingThread.interrupt();
    }
}
