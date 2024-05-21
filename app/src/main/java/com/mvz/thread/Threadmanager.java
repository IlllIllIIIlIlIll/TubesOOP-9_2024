package com.mvz.thread;

import com.mvz.Game;
import com.mvz.Sun;
import com.mvz.Tile;
import com.mvz.Zombie;
import com.mvz.menu.PauseMenu;
import com.mvz.zombies.LandZombieFactory;
import com.mvz.zombies.WaterZombieFactory;
import com.mvz.zombies.ZombieFactory;

import java.util.Scanner;

public class ThreadManager {
    private static ThreadManager instance;
    private Game game;
    private Scanner scanner;
    private Thread mainThread;
    private Thread zombieSpawningThread;
    private Thread sunGeneratingThread;
    private Thread positionUpdatingThread;

    private ThreadManager() {

    }

    public static synchronized ThreadManager getInstance() {
        if (instance == null) {
            instance = new ThreadManager();
        }
        return instance;
    }

    public void startThreads(Game game, Scanner scanner) {
        this.game = game;
        this.scanner = scanner;
        startMainThread();
        startZombieSpawningThread();
        startSunGeneratingThread();
        startPositionUpdatingThread();
    }

    private void startMainThread() {
        game.startGame();
        mainThread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                synchronized (scanner){
                    if (scanner.hasNextLine()) {
                        String input = scanner.nextLine();  

                        // // better user experience
                        // System.out.print("\033[H\033[2J");
                        // System.out.flush();

                        if (!input.equals("pause") && !input.equals("resume")){
                            game.userInput(input);
                        }
                        System.out.println("Sun value: " + Sun.getSun());
                        game.getPlayer().getDeck().printDeck();
                        System.out.println();
                        game.getMap().printMap();

                        if (Thread.currentThread().isInterrupted()) {
                            game.endGame(scanner);
                            break;
                        }
                        
                        
                        if (input.equalsIgnoreCase("pause")) {
                            pauseThreads();
                        } else if (input.equalsIgnoreCase("resume")) {
                            resumeThreads();
                            System.out.println("Game resumed!");
                        }
                    }
                }
            }
        });
        mainThread.start();
    }

private void startZombieSpawningThread() {
    zombieSpawningThread = new Thread(() -> {
        boolean isSpawningActive = false;
        boolean isFlagActive = false;
        long spawnStartTime = 0 * 1000;
        long spawnEndTime = 160 * 1000;
        long raidStartTime = 20 * 1000;
        long raidEndTime = 6 * 1000;

        while (!Thread.currentThread().isInterrupted()) {
            try {
                if (!game.isPaused()) {
                    long elapsedTime = game.getElapsedTime();

                    // Zombie spawning
                    if (elapsedTime >= spawnStartTime && elapsedTime <= spawnEndTime) {
                        if ((elapsedTime / 1000) % 3 == 0) {
                            if (!isSpawningActive) {
                                System.out.println("The zombies are coming!");
                                isSpawningActive = true;
                            }

                            for (int i = 0; i < 6; i++) {
                                float spawnRate = isFlagActive ? 0.5f : 0.3f;
                                if (game.getRandom().nextFloat() < spawnRate) {
                                    Zombie z;
                                    Tile tile = game.getMap().getTile(10, i);
                                    if (tile != null && tile.getOwners().isEmpty()) {
                                        ZombieFactory factory;
                                        String[] types;
                                        if (i == 2 || i == 3) {
                                            factory = new WaterZombieFactory();
                                            types = factory.getTypes();
                                        } else {
                                            factory = new LandZombieFactory();
                                            types = factory.getTypes();
                                        }
                                        int typeIndex = game.getRandom().nextInt(types.length);
                                        z = factory.createZombie(types[typeIndex], tile);
                                        game.getMap().placeZombie(z, i);
                                    }
                                }
                            }
                        }
                    } else if (elapsedTime > spawnEndTime) {
                        if (isSpawningActive) {
                            System.out.println("The zombies are out of army!");
                            isSpawningActive = false;
                        }
                    }

                    // Zombie flag
                    if (elapsedTime >= raidStartTime) {
                        long flagCycleTime = (elapsedTime - raidStartTime) % raidStartTime;
                        if (flagCycleTime >= 0 && flagCycleTime < raidEndTime) {
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

    

    private void startPositionUpdatingThread() {
        positionUpdatingThread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    Thread.sleep(1000);
                    if (!game.isPaused() && game.getMap() != null) { 
                        game.getMap().attackZombies();
                        game.getMap().setPosition();
                    }

                    long elapsedTimeMillis = game.getElapsedTime();
                    long elapsedTimeSeconds = elapsedTimeMillis / 1000;
                    if (game.getMap().getIsDefeated() || (game.getMap().getIsVictory() && elapsedTimeSeconds >= 160)) {
                        if (game.getMap().getIsDefeated()) {
                            System.out.println("\nYou have been defeated!");
                        } else {
                            System.out.println("\nYou have won the game!");
                        }
                        game.setPaused(false);
                        stopThreads();
                        break;
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
        new PauseMenu(game, scanner).displayMenu();
    }

    public void resumeThreads() {
        game.resumeGame();
        synchronized (game) {
            game.notifyAll(); // stop game biar ga nunggu lagi (wait)
        }
    }

    public void stopThreads() {
        if (mainThread != null) mainThread.interrupt();
        if (zombieSpawningThread != null) zombieSpawningThread.interrupt();
        if (sunGeneratingThread != null) sunGeneratingThread.interrupt();
        if (positionUpdatingThread != null) positionUpdatingThread.interrupt();
    }
    
}
