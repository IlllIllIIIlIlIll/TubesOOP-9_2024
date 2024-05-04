package com.mvz.thread;

import com.mvz.Game;
import java.util.Scanner;

public class ThreadManager {
    private Game game;
    private Scanner scanner;
    private Thread mainThread;
    private Thread zombieSpawningThread;
    private Thread sunGeneratingThread;
    private Thread positionUpdatingThread;

    public ThreadManager(Game game, Scanner scanner) {
        this.game = game;
        this.scanner = scanner;
    }

    public void startThreads() {
        startMainThread();
        startZombieSpawningThread();
        startSunGeneratingThread();
        startPositionUpdatingThread();
    }



    private void startMainThread() {
        game.startGame();
        mainThread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                String input = scanner.nextLine();  
                game.getMap().printMap();
                if (input.equalsIgnoreCase("pause")) {
                    pauseThreads();
                    System.out.println("Game paused!");
                } else if (input.equalsIgnoreCase("resume")) {
                    resumeThreads();
                    System.out.println("Game resumed!");
                }
            }
        });
        mainThread.start();
    }



    private void startZombieSpawningThread() {
        zombieSpawningThread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    if (!game.isPaused()) {
                        game.startSpawningZombies();
                    }
                    Thread.sleep(5000); 
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        zombieSpawningThread.start();
    }



    private void startSunGeneratingThread() {
        sunGeneratingThread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    Thread.sleep(3000);
                    if (!game.isPaused()) {
                        game.generateSun(); 
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); 
                }
            }
        });
        sunGeneratingThread.start();
    }

    private void startPositionUpdatingThread() {
        positionUpdatingThread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    Thread.sleep(1000); 
                    if (!game.isPaused()) {
                        game.getMap().setPosition();
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        positionUpdatingThread.start();
    }
    


    
    public void pauseThreads() {
        game.pauseGame();
    }

    public void resumeThreads() {
        game.resumeGame();
    }

    public void stopThreads() {
        if (mainThread != null) mainThread.interrupt();
        if (zombieSpawningThread != null) zombieSpawningThread.interrupt();
        if (sunGeneratingThread != null) sunGeneratingThread.interrupt();
        if (positionUpdatingThread != null) positionUpdatingThread.interrupt();
    }
}
