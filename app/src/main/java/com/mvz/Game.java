package com.mvz;

import java.util.Random;
import java.util.Scanner;

import com.mvz.exceptionhandling.InvalidInputException;
import com.mvz.exceptionhandling.InvalidTileException;
import com.mvz.plants.*;
import com.mvz.menu.EndGameMenu;

public class Game {
    private Player player; 
    private Map map;
    private transient Random random; 
    private boolean isPaused = false;
    private long elapsedTime = 0;
    private long startTime = 0;

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_RED = "\u001B[31m";

    // untuk player yang ingin melakukan save, tidak untuk diakses yang lain
    private int saveSun = 0;

    public Player getPlayer() {
        return player;
    }
    
    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public Random getRandom() {
        return random;
    }    

    public Integer getSavesun(){
        return saveSun;
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
            System.out.println(ANSI_RED + "INVALID TILE: " + e.getMessage() + ANSI_RESET);
        } catch (InvalidInputException e) {
            System.out.println(ANSI_RED + "INVALID INPUT: " + e.getMessage() + ANSI_RESET);
        }
    }

    public void checkInput(String input) throws InvalidInputException, InvalidTileException, NumberFormatException {
        String[] kata = input.split(" ");
        if (kata.length >= 4 && kata[0].equals("tanam")) {
            try {
                int x = Integer.parseInt(kata[kata.length-2]);
                int y = Integer.parseInt(kata[kata.length-1]);

                StringBuilder plantNameBuilder = new StringBuilder();
                for (int i = 1; i < kata.length - 2; i++) {
                    plantNameBuilder.append(kata[i]);
                    if (i < kata.length - 3) {
                        plantNameBuilder.append(" ");
                    }
                }
                String plantName = plantNameBuilder.toString();
                
                if (x > 0 && x < 10 && y > 0 && y < 7) {
                    Tile tile = map.getTile(x, y-1);
                    if (createThePlant(plantName, tile) != null) {
                        Plant plant = createThePlant(plantName, tile);
                        if (plant.canBuyThePlant()) {
                            if (plant.isReadyToBePlanted()) {
                                placePlant(plant, x, y-1);
                                plant.setLastPlantedTime(System.currentTimeMillis());
                            } else {
                                throw new InvalidInputException(ANSI_RED + plant.getName() + " is on cooldown!" + ANSI_RESET);
                            }
                        } else {
                            throw new InvalidInputException(ANSI_RED + "Sun yang kamu miliki tidak cukup untuk menanam " + plantName + ":(" + ANSI_RESET);
                        }
                    } else {
                        throw new InvalidInputException(ANSI_RED + "Tidak ada plant yang bernama "+ plantName + " di deck kamu!" + ANSI_RESET);
                    }
                } else {
                    throw new InvalidInputException(ANSI_RED + "Masukkan koordinat tile yang valid (1<x<11 dan 0<y<7)!" + ANSI_RESET);
                }
            } catch (NumberFormatException e) {
                throw new InvalidInputException(ANSI_RED + "Masukkan input dengan format \"tanam <nama plant> x y\"\nx dan y adalah koordinat tile di map yang valid (integer)" + ANSI_RESET);
            }
        } else if (kata.length == 3 && kata[0].equals("gali")) {
            try {
                int x = Integer.parseInt(kata[1]);
                int y = Integer.parseInt(kata[2]);
                if (x > 0 && x < 10 && y > 0 && y < 7) {
                    removePlant(x, y-1);
                }
            } catch (NumberFormatException e) {
                throw new InvalidInputException(ANSI_RED + "Masukkan input dengan format \"gali x y\"\nx dan y adalah koordinat tile di map yang valid (integer)" + ANSI_RESET);
            }
        } else if (kata[0] == "") {
            // do nothing
        } else {
            throw new InvalidInputException(ANSI_RED + "Masukkan input dengan format \"tanam <nama plant> x y\" atau \"gali x y\"\nx dan y adalah koordinat tile di map yang valid\n" + ANSI_RESET);
        }
    }

    public Plant createThePlant(String input, Tile tile) {
        Plant plantToPlant = null;
        for (Plant tumbuhan : player.getDeck().getPlants()) {
            if (tumbuhan.getName().toLowerCase().equals(input.toLowerCase())) {
                plantToPlant = tumbuhan;
                break;
            }
        }
        if (plantToPlant != null) {
            if (plantToPlant.isAquatic()) {
                WaterPlantFactory waterPlantFact = new WaterPlantFactory();
                return waterPlantFact.createPlant(plantToPlant.getName(), tile);
            } else {
                LandPlantFactory landPlantFactory = new LandPlantFactory();
                return landPlantFactory.createPlant(plantToPlant.getName(), tile);
            }
        } else return null;
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
                System.out.println(ANSI_CYAN + "Tanaman berhasil ditanam 1" + ANSI_RESET);
            }
            else if (!p.isAquatic() && containsLilypad) {
                if (lilypad.addOnLilypad(p)) {
                    Sun.decreaseSun(p.getCost());
                    System.out.println(ANSI_CYAN + "Tanaman berhasil ditanam 2" + ANSI_RESET);
                } else {
                    throw new InvalidTileException(ANSI_RED + "Plant ga bisa ditanam di tile ini 1" + ANSI_RESET);
                }
            }
            else {
                throw new InvalidTileException(ANSI_RED + "Plant ga bisa ditanam di tile ini 2" + ANSI_RESET);
            }
        }
        else { // kalo tile daratan
            if (!p.isAquatic() && !containsPlant) {
                targetTile.addOwner(p);
                Sun.decreaseSun(p.getCost());
                System.out.println(ANSI_CYAN + "Tanaman berhasil ditanam 3" + ANSI_RESET);
            }
            else {  
                throw new InvalidTileException(ANSI_RED + "Plant ga bisa ditanam di tile ini 3" + ANSI_RESET);
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
            System.out.printf(ANSI_CYAN + "%s berhasil digali dari tile (%d,%d).\n", plantToRemove.getName(), x, y+1 + ANSI_RESET);
        }
        else {
            throw new InvalidTileException(ANSI_RED + "Penggalian gagal. Tidak ada plant di tile ("+ x + "," + y + ")." + ANSI_RESET);
        }        
    }
    
    public void startGame() {
        startTime = System.currentTimeMillis();
    }

    public synchronized void pauseGame() {
        isPaused = true;
        elapsedTime += System.currentTimeMillis() - startTime;
        saveSun = Sun.getSun();
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
    
    public void endGame(Scanner scanner) {
        new EndGameMenu(player, scanner).displayMenu();
    }
}
