package com.mvz;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import com.mvz.exceptionhandling.InvalidInputException;
import com.mvz.exceptionhandling.InvalidTileException;
import com.mvz.plants.LandPlantFactory;
import com.mvz.plants.Lilypad;
import com.mvz.plants.WaterPlantFactory;
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

    public Map getMap(){
        return map;
    }

    public void setMap(Map map){
        this.map = map;
    }

    public Player getPlayer() {
        return player;
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

    public void checkInput(String input) throws InvalidInputException {
        String[] kata = input.split(" ");
        if (kata.length == 4 && kata[0].equals("tanam")) {
            try {
                int x = Integer.parseInt(kata[2]);
                int y = Integer.parseInt(kata[3]) - 1;
                if (x < 1 || x > 9 || y < 0 || y > 5) {
                    Tile tile = map.getTile(x, y);
                    if (player.getDeck().createThePlant(kata[1], tile) != null) {
                        Plant plant = player.getDeck().createThePlant(kata[1], tile);
                        if (Sun.getSun() >= plant.getCost()) {
                            if (plant.canPlant()) {
                                placePlant(plant, x, y);
                            } else {
                                throw new InvalidInputException(plant.getName() + " is on cooldown!");
                            }
                        } else {
                            throw new InvalidInputException("Sun yang kamu miliki tidak cukup untuk menanam " + kata[1] + ":(");
                        }
                    } else {
                        throw new InvalidInputException("Tidak ada plant yang bernama "+ kata[1] + "di deck kamu!");
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
                int y = Integer.parseInt(kata[2]) - 1;
                if (x < 1 || x > 9 || y < 0 || y > 5) {
                    Tile tile = map.getTile(x, y);
                    removePlant(x, y);
                }
            } catch (NumberFormatException e) {
                throw new InvalidInputException("Masukkan input dengan format \"gali x y\"\nx dan y adalah koordinat tile di map yang valid (integer)");
            }
        } else {
            throw new InvalidInputException("Masukkan input dengan format \"tanam <nama plant> x y\" atau \"gali x y\"\nx dan y adalah koordinat tile di map yang valid");
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
                if (lilypad.addOnLilypad()) {
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
            System.out.printf("%s berhasil digali dari tile (%d,%d).", plantToRemove.getName(), x, y+1);
        }
        else {
            throw new InvalidTileException("Penggalian gagal. Tidak ada plant di tile ("+ x + "," + (y+1) + ").");
        }        
   }

    public void startSpawningZombies() {
        ZombieFactory landFactory = new LandZombieFactory();
        ZombieFactory waterFactory = new WaterZombieFactory();
        zombieThread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(5000); 
                } catch (InterruptedException e) {
                    // Handle exception
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
        if (!sunThread.isAlive()) {
            generateSun();
        }
        if (!zombieThread.isAlive()) {
            startSpawningZombies();
        }
    }

    public long getElapsedTime() {
        if (isPaused) {
            return elapsedTime;
        } else {
            return elapsedTime + System.currentTimeMillis() - startTime;
        }
    }

    public boolean isPaused() {
        return isPaused;
    }

}
