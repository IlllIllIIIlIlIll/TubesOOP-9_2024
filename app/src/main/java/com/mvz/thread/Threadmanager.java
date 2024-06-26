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

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_RED = "\u001B[31m";

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

                        // better user experience
                        System.out.print("\033[H\033[2J");
                        System.out.flush();

                        if (!input.equals("pause") && !input.equals("resume")){
                            game.userInput(input);
                        }
                        game.getPlayer().getDeck().printDeck();
                        System.out.println(ANSI_CYAN + "Elapsed time: " + game.getElapsedTime()/1000 + ANSI_RESET);
                        System.out.println(ANSI_CYAN + "Sun value: " + Sun.getSun() + ANSI_RESET);
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
                            System.out.println(ANSI_CYAN + "Game resumed!" + ANSI_RESET);
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
            long spawnStartTime = 20 * 1000;
            long spawnEndTime = 160 * 1000;
            long raidStartTime = 80 * 1000;
            long raidEndTime = 6 * 1000;

            while (!Thread.currentThread().isInterrupted()) {
                try {
                    if (!game.isPaused()) {
                        long elapsedTime = game.getElapsedTime();

                        // Zombie spawning
                        if (elapsedTime >= spawnStartTime && elapsedTime <= spawnEndTime) {
                            if ((elapsedTime / 1000) % 3 == 0) {
                                if (!isSpawningActive) {
                                    System.out.println(ANSI_CYAN + "The zombies are coming!" + ANSI_RESET);
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
                                System.out.println(ANSI_CYAN + "The zombies are out of army!" + ANSI_RESET);
                                isSpawningActive = false;
                            }
                        }

                        // Zombie flag
                        if (elapsedTime >= raidStartTime) {
                            long flagCycleTime = (elapsedTime - raidStartTime) % raidStartTime;
                            if (flagCycleTime >= 0 && flagCycleTime < raidEndTime) {
                                if (!isFlagActive) {
                                    System.out.println(ANSI_CYAN + "A Huge Wave of Zombies is Approaching!" + ANSI_RESET);
                                    game.getMap().setMaxZombies(27);
                                    isFlagActive = true;
                                }
                            } else {
                                if (isFlagActive) {
                                    System.out.println(ANSI_CYAN + "Flag wave ended!" + ANSI_RESET);
                                    game.getMap().setMaxZombies(2);
                                    isFlagActive = false;
                                }
                            }
                        }
                    }
                    Thread.sleep(1000); 
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
                    long elapsedSeconds = game.getElapsedTime() / 1000;
                    long interval = elapsedSeconds % 200;
                    
                    if (interval < 100) {
                        long sleepDuration = 5000 + game.getRandom().nextInt(5000);
                        Thread.sleep(sleepDuration);

                        elapsedSeconds = game.getElapsedTime() / 1000;
                        interval = elapsedSeconds % 200;
                        if (interval < 100 && !game.isPaused() && game.getMap() != null) {
                            game.generateSun();
                        }
                    } else {
                        // Sleep period
                        long sleepTime = (200 - interval) * 1000;
                        Thread.sleep(sleepTime);
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

                    long elapsedTimeSeconds = game.getElapsedTime() / 1000;
                    if (game.getMap().getIsDefeated() || (game.getMap().getIsVictory() && elapsedTimeSeconds >= 160)) {
                        if (game.getMap().getIsDefeated()) {
                            System.out.println(ANSI_CYAN + "\nYou have been defeated!" + ANSI_RESET);
                        } else {
                            System.out.println(ANSI_CYAN + "\nYou have won the game!" + ANSI_RESET);
                        }
                        game.pauseGame();
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
        // better user experience
        System.out.print("\033[H\033[2J");
        System.out.flush();
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
