package com.mvz.thread;

import com.mvz.Game;
import com.mvz.Sun;
import com.mvz.menu.PauseMenu;

import java.util.Scanner;

// Class for managing game threads
public class ThreadManager {
    private static ThreadManager instance;
    private Game game;
    private Scanner scanner;
    private Thread mainThread;
    private Thread zombieSpawningThread;
    private Thread sunGeneratingThread;
    private Thread positionUpdatingThread;

    // Private constructor for singleton pattern
    private ThreadManager() {

    }

    // Method to get the single instance of ThreadManager
    public static synchronized ThreadManager getInstance() {
        if (instance == null) {
            instance = new ThreadManager();
        }
        return instance;
    }

    // Method to start all the game-related threads
    public void startThreads(Game game, Scanner scanner) {
        this.game = game;
        this.scanner = scanner;
        startMainThread();
        startZombieSpawningThread();
        startSunGeneratingThread();
        startPositionUpdatingThread();
    }

    // Method to start the main game thread
    private void startMainThread() {
        game.startGame();
        mainThread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                String input = scanner.nextLine();  

                // Calculate the elapsed time in seconds
                long elapsedTimeMillis = game.getElapsedTime();
                long elapsedTimeSeconds = elapsedTimeMillis / 1000;
                System.out.println("Game second: " + elapsedTimeSeconds + " seconds");

                game.userInput(input);
                System.out.println("Sun value: " + Sun.getSun());
                game.getPlayer().getDeck().printDeck();
                System.out.println();
                game.getMap().printMap();

                // Check game victory or defeat conditions
                if (game.getMap().getIsDefeated() || (game.getMap().getIsVictory() && elapsedTimeSeconds >= 160)) {
                    if (game.getMap().getIsDefeated()) {
                        System.out.println("\nYou have been defeated!");
                    } else {
                        System.out.println("\nYou have won the game!");
                    }
                    game.setPaused(false);
                    game.endGame(scanner);
                    break;
                }
                
                // Handle pause and resume commands
                if (input.equalsIgnoreCase("pause")) {
                    pauseThreads();
                } else if (input.equalsIgnoreCase("resume")) {
                    resumeThreads();
                    System.out.println("Game resumed!");
                }
            }
        });
        mainThread.start();
    }

    // Method to start the zombie spawning thread
    private void startZombieSpawningThread() {
        zombieSpawningThread = new Thread(() -> {
            boolean isSpawningActive = false;
            boolean isFlagActive = false;
            long spawnStartTime = 0 * 1000;
            long spawnEndTime = 160 * 1000;
        
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    if (!game.isPaused()) {
                        long elapsedTime = game.getElapsedTime();

                        // Zombie spawning
                        int pass = (int) Math.ceil(elapsedTime/1000);
                        if (elapsedTime >= spawnStartTime && elapsedTime <= spawnEndTime && (pass % 3) == 0) {
                            if (!isSpawningActive) {
                                System.out.println("The zombies are coming!");
                                isSpawningActive = true;
                            }
                            game.startSpawningZombies(isFlagActive);
                        } else if (elapsedTime > spawnEndTime) {
                            if (isSpawningActive) {
                                System.out.println("The zombies are out of army!");
                                isSpawningActive = false;
                            }
                        }
        
                        // Zombie flag wave logic
                        if (elapsedTime >= 80 * 1000) {
                            long flagCycleTime = (elapsedTime - 80 * 1000) % 80000;
                            if (flagCycleTime >= 0 && flagCycleTime < 6000) {
                                if (!isFlagActive) {
                                    System.out.println("A Huge Wave of Zombies is Approaching!");
                                    game.getMap().setMaxZombies(27);
                                    isFlagActive = true;
                                }
                            } else {
                                if (isFlagActive) {
                                    System.out.println("Flag wave ended!");
                                    game.getMap().setMaxZombies(2);
                                    isFlagActive = false;
                                }
                            }
                        }
                    }
                    Thread.sleep(1000); // Check every second
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        zombieSpawningThread.start();
    }

    
    // Method to start the sun generating thread
    private void startSunGeneratingThread() {
        sunGeneratingThread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    Thread.sleep(5000 + game.getRandom().nextInt(5000));
                    if (!game.isPaused() && game.getMap() != null) {
                        game.generateSun();
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        sunGeneratingThread.start();
    }

    
    // Method to start the position updating thread
    private void startPositionUpdatingThread() {
        positionUpdatingThread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    Thread.sleep(1000);
                    if (!game.isPaused() && game.getMap() != null) { 
                        game.getMap().attackZombies();
                        game.getMap().setPosition();
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        positionUpdatingThread.start();
    }    

    // Method to pause all threads
    public void pauseThreads() {
        game.pauseGame();
        new PauseMenu(game).displayMenu();
    }

    // Method to resume all threads
    public void resumeThreads() {
        game.resumeGame();
        synchronized (game) {
            game.notifyAll();   // Notify all waiting threads
        }
    }

    // Method to stop all threads
    public void stopThreads() {
        if (mainThread != null) mainThread.interrupt();
        if (zombieSpawningThread != null) zombieSpawningThread.interrupt();
        if (sunGeneratingThread != null) sunGeneratingThread.interrupt();
        if (positionUpdatingThread != null) positionUpdatingThread.interrupt();
    }
}
