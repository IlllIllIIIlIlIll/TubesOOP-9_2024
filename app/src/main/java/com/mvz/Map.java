package com.mvz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Iterator;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.mvz.plants.*;
import com.mvz.zombies.Dolphinrider;
import com.mvz.zombies.Jackinthebox;
import com.mvz.zombies.Polevaulting;

public class Map {
    private List<Zombie> zombieOnTile;
    private Tile[][] tile;
    private boolean isDefeated = false;
    private boolean isVictory = false;
    private int MAX_ZOMBIES = 2;


    public Map() {
        tile = new Tile[11][6];
        zombieOnTile = new ArrayList<>();

        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 6; j++) {
                boolean isAquatic = (j == 2 || j == 3);
                tile[i][j] = new Tile(i, j, isAquatic);
            }
        }

        // tanaman testing aja
        // Wallnut a = new Wallnut(5, 0);
        // tile[5][0].addOwner(a);
        // Wallnut b = new Wallnut(5, 1);
        // tile[5][1].addOwner(b);
        Lilypad c = new Lilypad(5, 2);
        tile[5][2].addOwner(c);
        Lilypad d = new Lilypad(5, 3);
        tile[5][3].addOwner(d);
        // Wallnut e = new Wallnut(5, 4);
        // tile[5][4].addOwner(e);
        // Wallnut f = new Wallnut(5, 5);
        // tile[5][5].addOwner(f);

        // Wallnut g = new Wallnut(3, 4);
        // tile[3][4].addOwner(g);
        // Wallnut h = new Wallnut(3, 5);
        // tile[3][5].addOwner(h);
        // Wallnut i = new Wallnut(3, 0);
        // tile[3][0].addOwner(i);
        // Wallnut j = new Wallnut(3, 1);
        // tile[3][1].addOwner(j);
    }

    public Tile getTile(int x, int y) {
        return this.tile[x][y];
    }

    public int getNumberOfRows() {
        return this.tile[0].length;
    }

    public int getNumberOfColumns() {
        return this.tile.length;
    }

    public boolean getIsDefeated() {
        return isDefeated;
    }

    public void setIsDefeated(boolean isDefeated){
        this.isDefeated = isDefeated;
    }

    public boolean getIsVictory() {
        return isVictory;
    }

    public void setIsVictory(boolean isVictory){
        this.isVictory = isVictory;
    }

    public int getMaxZombies(){
        return MAX_ZOMBIES;
    }

    public void setMaxZombies(int maxZombies) {
        this.MAX_ZOMBIES = maxZombies;
    }    

    public void printMap() {
        String darkWatercolor = "\033[0;34m"; 
        String darkLandcolor = "\033[0;32m";
        String housecolor = "\033[1;35m";
        String spawncolor = "\033[0;33m";
        String zombiecolor = "\033[1;31m"; 
        String plantcolor = "\033[1;92m"; 
        String reset = "\033[0m";
    
        System.out.println("Sun value: " + Sun.getSun());
        for (int j = getNumberOfRows() - 1; j >= 0; j--) {  // Start from y = 5 and go down to y = 0
            for (int i = 0; i < getNumberOfColumns(); i++) {
                Tile currentTile = getTile(i, j);
                String color;
                if (i == 0) {
                    color = housecolor;
                } else if (i == 10) {
                    color = spawncolor;
                } else if (j == 2 || j == 3) {
                    color = darkWatercolor;
                } else {
                    color = darkLandcolor;
                }
                System.out.print(color + "[");
                if (currentTile.getOwners().size() > 0) {
                    for (int k = 0; k < 5; k++) {
                        if (k < currentTile.getOwners().size()) {
                            Character owner = currentTile.getOwners().get(k);
                            if (owner instanceof Zombie) {
                                System.out.print(zombiecolor + owner.getName().charAt(0) + reset + color);
                            } else if (owner instanceof Plant) {
                                System.out.print(plantcolor + owner.getName().charAt(0) + reset + color);
                            } else {
                                System.out.print(" ");
                            }
                        } else {
                            System.out.print(" ");
                        }
                    }
                } else {
                    System.out.print("     ");
                }
                System.out.print("]" + reset);
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
    }
    
    

    public void setPosition() {
        List<Zombie> zombiesToRemove = new ArrayList<>();

        Iterator<Zombie> iterator = zombieOnTile.iterator();
        while (iterator.hasNext()) {
            Zombie z = iterator.next();
            if (z.getHealth() <= 0) {
                zombiesToRemove.add(z);
                continue;
            }

            int x = z.getXChar();
            int y = z.getYChar();

            // mencegah akses di luar batasan
            if (x > 0){
                Tile currentTile = getTile(x, y);
                Tile nextTile = getTile(x - 1, y);
                boolean hasAlivePlantInCurrentTile = processTileForZombie(currentTile, z);
                boolean hasAlivePlantInNextTile = processTileForZombie(nextTile, z);
        
                // zombie belum paling ujung dan bisa gerak (mov.spd tidak cooldown)
                if (x - 1 >= 0 && z.getCM()) {
                    // System.out.println(z.getName() + " ada tanaman disekitarnya? " + hasAlivePlantInCurrentTile + " " + hasAlivePlantInNextTile);
                    
                    // jika ada tidak ada tanaman (hidup) di tile x dan x-1 
                    if (!hasAlivePlantInCurrentTile && !hasAlivePlantInNextTile) {
                        // zombie bergerak
                        moveZombie(currentTile, nextTile, z);
                        // cooldown atk.spd reset
                        z.resetAttackTimer();
                        z.setCM();

                        if (nextTile.getX() == 0){
                            setIsDefeated(true);
                            return;
                        }

                    } else {
                        // masuk ke method penyerangan tanaman
                        z.initiateAttack();
                        z.setCM(false);
                    }

                } else {
                    z.setCM(false);
                }
            }
        }

        // Remove the deads!
        zombieOnTile.removeAll(zombiesToRemove);
        removeDeadOwners();
    }

    private void removeDeadOwners() {
        boolean hasZombies = false;
    
        for (Tile[] tiles : tile) {
            for (Tile t : tiles) {
                for (Character owner : new ArrayList<>(t.getOwners())) {
                    if (owner.getHealth() <= 0) {
                        t.removeOwner(owner);
                    } else if (owner instanceof Zombie) {
                        hasZombies = true;
                    }
                }
            }
        }
    
        if (!hasZombies) {
            setIsVictory(true);
        }
    }
    
    
    private boolean processTileForZombie(Tile tile, Zombie z) {
        for (Character owner : tile.getOwners()) {
            if (owner instanceof Plant) {
                Plant plant = (Plant) owner;
                if (plant.getHealth() > 0 && z.getcanAttack()) {
                    if ((z instanceof Polevaulting && !((Polevaulting) z).isJumping())
                        || (z instanceof Dolphinrider && !((Dolphinrider) z).isJumping())) {
                        
                        plant.decreaseHealth(9999.0f); 
                        z.action();
                        
                        Tile nextTile = getTile(z.getXChar() - 2, z.getYChar());
                        moveZombie(tile, nextTile, z);
                    } else if (z instanceof Jackinthebox){
                        plant.decreaseHealth(z.getAD());
                        z.action();
                    }
                    else {
                        plant.decreaseHealth(z.getAD());
                    }
                    return true;
                }
            }
        }
        return false;
    }

    private void moveZombie(Tile currentTile, Tile nextTile, Zombie zombie) {
        currentTile.removeOwner(zombie);
        nextTile.addOwner(zombie);
        zombie.setXChar(nextTile.getX());
        zombie.setYChar(nextTile.getY());
    
        if (nextTile.getX() == 0) {
            isDefeated = true;
        }
    }

    public void placeZombie(Zombie z, int y) {
        if (zombieOnTile.size() < MAX_ZOMBIES) {
            this.zombieOnTile.add(z);
            int x = z.getXChar();
            tile[x][y].addOwner(z);
        }
    }







    public void attackZombies() {
        HashMap<Tile, Float> damageMap = new HashMap<>();

        for (Tile[] row : tile) {
            for (Tile t : row) {
                for (Character owner : t.getOwners()) {
                    if (owner instanceof Plant) {
                        Plant plant = (Plant) owner;
                        if (!plant.getcanAttack()) {
                            continue;
                        }

                        int plantX = plant.getXChar();
                        int plantY = plant.getYChar();
                        int range = plant.getRange();
                        float attackDamage = plant.getAD();

                        switch (range) {
                            case 0:
                                // Do nothing
                                break;
                            case 1:
                                boolean actionPerformed = false;
                                if (attackTile(damageMap, plantX - 1, plantY, attackDamage, plant.getName())) {
                                    actionPerformed = true;
                                } else if (attackTile(damageMap, plantX, plantY, attackDamage, plant.getName())) {
                                    actionPerformed = true;
                                } else if (attackTile(damageMap, plantX + 1, plantY, attackDamage, plant.getName())) {
                                    actionPerformed = true;
                                }
                                if (actionPerformed) {
                                    owner.action();
                                    System.out.println(owner.getHealth());
                                }
                                break;
                            
                            case -1:
                                for (int x = plantX; x < 11; x++) {
                                    if (attackTile(damageMap, x, plantY, attackDamage, plant.getName())) {
                                        break;
                                    }
                                }
                                break;
                            case 9:
                                for (int x = 1; x < 10; x++) {
                                    attackTile(damageMap, x, plantY, attackDamage, plant.getName());
                                }
                                owner.action();
                                break;
                            case 3:
                                attackTile(damageMap, plantX, plantY, attackDamage, plant.getName());
                                attackTile(damageMap, plantX - 1, plantY - 1, attackDamage, plant.getName());
                                attackTile(damageMap, plantX - 1, plantY, attackDamage, plant.getName());
                                attackTile(damageMap, plantX - 1, plantY + 1, attackDamage, plant.getName());
                                attackTile(damageMap, plantX, plantY - 1, attackDamage, plant.getName());
                                attackTile(damageMap, plantX, plantY + 1, attackDamage, plant.getName());
                                attackTile(damageMap, plantX + 1, plantY - 1, attackDamage, plant.getName());
                                attackTile(damageMap, plantX + 1, plantY, attackDamage, plant.getName());
                                attackTile(damageMap, plantX + 1, plantY + 1, attackDamage, plant.getName());
                                owner.action();
                                break;
                            default:
                                // If there are other range types
                                break;
                        }

                        plant.setcanAttack(false); 
                    }
                }
            }
        }

        applyDamageToZombies(damageMap);
    }

    private boolean attackTile(HashMap<Tile, Float> damageMap, int x, int y, float damage, String plantName) {
        if (x < 0 || x >= 11 || y < 0 || y >= 6) {
            return false;
        }

        Tile targetTile = getTile(x, y);
        if (targetTile.getOwners().stream().anyMatch(owner -> owner instanceof Zombie)) {
            damageMap.put(targetTile, damageMap.getOrDefault(targetTile, 0f) + damage);

            if (plantName.equals("Snow pea")) {
                applyChillingEffectToZombies(targetTile);
            }

            return true;
        }

        return false;
    }

    private void applyChillingEffectToZombies(Tile targetTile) {
        for (Character owner : targetTile.getOwners()) {
            if (owner instanceof Zombie) {
                Zombie zombie = (Zombie) owner;
                if (!zombie.getCH()) {
                    float originalSpeed = zombie.getMS();
                    float originalAttackSpeed = zombie.getAS();
                    long timeRemainingToMove = zombie.getTimeRemainingToMove();
                    long timeRemainingToAttack = zombie.getTimeRemainingToAttack();

                    long newMoveTime = timeRemainingToMove * 2;
                    long newAttackTime = timeRemainingToAttack * 2;

                    zombie.setMSD(originalSpeed / 2);
                    zombie.setAS(originalAttackSpeed / 2);
                    zombie.setTimeRemainingToMove(newMoveTime);
                    zombie.setTimeRemainingToAttack(newAttackTime);
                    zombie.setCH();
                    System.out.println("Aduhh " + zombie.getName() + " dingin bang");

                    ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
                    executorService.schedule(() -> {
                        if (zombie.getCH()) {
                            long timePassed = 3000; 
                            long remainingMoveTime = (newMoveTime - timePassed + 999) / 2; 
                            long remainingAttackTime = (newAttackTime - timePassed + 999) / 2; 
                            zombie.setMSD(originalSpeed);
                            zombie.setAS(originalAttackSpeed);
                            zombie.setTimeRemainingToMove(remainingMoveTime);
                            zombie.setTimeRemainingToAttack(remainingAttackTime);
                            zombie.setCH();
                            System.out.println("Udah ga dingin bang, kata zombie " + zombie.getName());
                        }
                    }, 3, TimeUnit.SECONDS);
                }
            }
        }
    }

    private void applyDamageToZombies(HashMap<Tile, Float> damageMap) {
        for (Entry<Tile, Float> entry : damageMap.entrySet()) {
            Tile tile = entry.getKey();
            float damage = entry.getValue();

            for (Character owner : new ArrayList<>(tile.getOwners())) {
                if (owner instanceof Zombie) {
                    Zombie zombie = (Zombie) owner;
                    zombie.decreaseHealth(damage);
                    System.out.println("BOOM! " + zombie.getName() + " terkena damage " + damage + ", sisa darah " + zombie.getHealth() + "!");
                }
            }
        }
    }

    public void initExecutors() {
        for (Tile[] row : tile) {
            for (Tile t : row) {
                for (Character owner : t.getOwners()) {
                    if (owner instanceof Zombie) {
                        ((Zombie) owner).initZombieScheduledExecutors();
                    } else if (owner instanceof Sunflower) {
                        ((Sunflower) owner).initSunflowerExecutorService();
                    }
                    // Jika ada ScheduledExecutorService yang ingin diinstansiasi ulang, buat load
                }
            }
        }
    }
}
