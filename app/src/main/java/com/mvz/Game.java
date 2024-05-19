package com.mvz;

import java.util.Random;
import java.util.Scanner;

import com.mvz.exceptionhandling.InvalidInputException;
import com.mvz.exceptionhandling.InvalidTileException;
import com.mvz.plants.*;
import com.mvz.zombies.*;
import com.mvz.menu.EndGameMenu;

public class Game {
    private Player player; 
    private Map map;
    private transient Random random; 
    private boolean isPaused = false;
    private long elapsedTime = 0;
    private long startTime = 0;

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public Random getRandom() {
        return random;
    }    

    public Game(Player player) {
        this.player = player;
        this.map = new Map();
        this.random = new Random(); 
    }

    
    public Game() {
        this.random = new Random();
    }

    public void generateSun() {
        if (!isPaused) {
            Sun.increaseSun(25);
        }
    }    

    public void userInput(String input) {
        try {
            checkInput(input);
        } catch (InvalidTileException e) {
            System.out.println("INVALID TILE: " + e.getMessage());
        } catch (InvalidInputException e) {
            System.out.println("INVALID INPUT: " + e.getMessage());
        }
    }

    public void checkInput(String input) throws InvalidInputException, InvalidTileException {
        String[] kata = input.toLowerCase().split(" ");
        if (kata.length == 4 && kata[0].equals("tanam")) {
            try {
                int x = Integer.parseInt(kata[2]);
                int y = Integer.parseInt(kata[3]);
                if (x > 0 && x < 10 && y > 0 && y < 7) {
                    Tile tile = map.getTile(x, y-1);
                    if (player.getDeck().createThePlant(kata[1], tile) != null) {
                        Plant plant = player.getDeck().createThePlant(kata[1], tile);
                        if (plant.canBuyThePlant()) {
                            if (plant.isReadyToBePlanted()) {
                                placePlant(plant, x, y-1);
                                plant.setLastPlantedTime(System.currentTimeMillis());
                            } else {
                                throw new InvalidInputException(plant.getName() + " is on cooldown!");
                            }
                        } else {
                            throw new InvalidInputException("Sun yang kamu miliki tidak cukup untuk menanam " + kata[1] + ":(");
                        }
                    } else {
                        throw new InvalidInputException("Tidak ada plant yang bernama "+ kata[1] + " di deck kamu!");
                    }
                } else {
                    throw new InvalidInputException("Masukkan koordinat tile yang valid (1<x<11 dan 0<y<7)!");
                }
            } catch (NumberFormatException e) {
                throw new InvalidInputException("Masukkan input dengan format \"tanam <nama plant> x y\"\nx dan y adalah koordinat tile di map yang valid (integer)");
            }
        } else if (kata.length == 3 && kata[0].equals("gali")) {
            try {
                int x = Integer.parseInt(kata[1]);
                int y = Integer.parseInt(kata[2]);
                if (x > 0 && x < 10 && y > 0 && y < 7) {
                    removePlant(x, y-1);
                }
            } catch (NumberFormatException e) {
                throw new InvalidInputException("Masukkan input dengan format \"gali x y\"\nx dan y adalah koordinat tile di map yang valid (integer)");
            }
        } else if (kata[0] == "") {
            // do nothing
        } else {
            throw new InvalidInputException("Masukkan input dengan format \"tanam <nama plant> x y\" atau \"gali x y\"\nx dan y adalah koordinat tile di map yang valid\n");
        }
        
    }

    public void placePlant(Plant p, int x, int y) throws InvalidTileException {
        Tile targetTile = map.getTile(x, y);

        boolean containsPlant = false;
        boolean containsLilypad = false;

        Lilypad lilypad = new Lilypad();

        for (Character owner : targetTile.getOwners()) {
            if (owner instanceof Plant) {
                containsPlant = true;
                if (owner instanceof Lilypad) {
                    containsLilypad = true;
                    lilypad = (Lilypad) owner;
                }
                break;
            }            
        }

        // kalo udah berhasil ditesting, bakal digabungin semua conditional enih
        if (targetTile.getIsA()) {  
            if ((!containsPlant && (p.isAquatic()))) {
                targetTile.addOwner(p);
                Sun.decreaseSun(p.getCost());
                System.out.println("Tanaman berhasil ditanam 1");
            }
            else if (!p.isAquatic() && containsLilypad) {
                if (lilypad.addOnLilypad(p)) {
                    Sun.decreaseSun(p.getCost());
                    System.out.println("Tanaman berhasil ditanam 2");
                } else {
                    throw new InvalidTileException("Plant ga bisa ditanam di tile ini 1");
                }
            }
            else {
                throw new InvalidTileException("Plant ga bisa ditanam di tile ini 2");
            }
        }
        else { // kalo tile daratan
            if (!p.isAquatic() && !containsPlant) {
                targetTile.addOwner(p);
                Sun.decreaseSun(p.getCost());
                System.out.println("Tanaman berhasil ditanam 3");
            }
            else {  
                throw new InvalidTileException("Plant ga bisa ditanam di tile ini 3");
            }
        }
    }    

    public void removePlant(int x, int y) throws InvalidTileException {
        Tile targetTile = map.getTile(x, y);

        Plant plantToRemove = null;
        for (Character owner : targetTile.getOwners()) {
            if (owner instanceof Plant) {
                plantToRemove = (Plant) owner;
                break;
            }            
        }

        if (plantToRemove != null) {
            targetTile.removeOwner(plantToRemove);
            System.out.printf("%s berhasil digali dari tile (%d,%d).\n", plantToRemove.getName(), x, y);
        }
        else {
            throw new InvalidTileException("Penggalian gagal. Tidak ada plant di tile ("+ x + "," + y + ").");
        }        
    }


   
    public void startSpawningZombies(boolean isFlagActive) {
        ZombieFactory landFactory = new LandZombieFactory();
        ZombieFactory waterFactory = new WaterZombieFactory();
        new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                synchronized (Game.this) {
                    while (isPaused) {
                        try {
                            Game.this.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                    if (map == null) { // end game
                        return;
                    }
                }
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

                for (int i = 0; i < 6; i++) {
                    float spawnRate = isFlagActive ? 0.5f : 0.3f;
                    if (random.nextFloat() < spawnRate) {
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
        }).start();
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

    public synchronized void setPaused(boolean isPaused){
        this.isPaused = isPaused;
    }
    
    public void endGame(Scanner scanner) {
        new EndGameMenu(player, scanner).displayMenu();
    }
}
