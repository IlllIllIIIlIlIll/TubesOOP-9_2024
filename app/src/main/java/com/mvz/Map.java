package com.mvz;

import java.util.ArrayList;
import java.util.List;

public class Map {
    private List<Plant> plantOnTile;
    private List<Zombie> zombieOnTile;
    private Tile[][] tile;
    private List<List<Zombie>> zombiesByY;

    public Map() {
        tile = new Tile[11][6];
        plantOnTile = new ArrayList<>();
        zombieOnTile = new ArrayList<>();
        zombiesByY = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            this.zombiesByY.add(new ArrayList<>());
        }

        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 6; j++) {
                boolean isAquatic = (j == 2 || j == 3);
                tile[i][j] = new Tile(i, j, isAquatic);
            }
        }
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

    public void printMap() {
        String watercolor = "\033[1;34m";
        String landcolor = "\033[0;32m";
        String housecolor = "\033[1;35m";
        String spawncolor = "\033[0;33m";
        String reset = "\033[0m";

        System.out.println("Sun value: " + Sun.getSun());
        for (int j = 0; j < getNumberOfRows(); j++) {
            for (int i = 0; i < getNumberOfColumns(); i++) {
                Tile currentTile = getTile(i, j);
                String color;
                if (i == 0) {
                    color = housecolor;
                } else if (i == 10) {
                    color = spawncolor;
                } else if (j == 2 || j == 3) {
                    color = watercolor;
                } else {
                    color = landcolor;
                }
                System.out.print(color + "[");
                if (currentTile.getOwners().size() > 0) {
                    for (int k = 0; k < 5; k++) {
                        if (k < currentTile.getOwners().size()) {
                            System.out.print(currentTile.getOwners().get(k).getName().charAt(0));
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

        // Iterate over zombies
        for (Zombie z : zombieOnTile) {
            if (z.getHealth() <= 0) {
                zombiesToRemove.add(z);
                continue;
            }

            int x = z.getXChar();
            int y = z.getYChar();

            boolean moved = false;
            if (x - 1 >= 0 && z.getCM()) {
                Tile currentTile = getTile(x, y);
                Tile nextTile = getTile(x - 1, y);
                boolean hasAlivePlantInCurrentTile = processTileForZombie(currentTile, z);
                boolean hasAlivePlantInNextTile = processTileForZombie(nextTile, z);

                if (!hasAlivePlantInCurrentTile && !hasAlivePlantInNextTile) {
                    moveZombie(currentTile, nextTile, z);
                    moved = true;
                }
                z.setCM(); // Reset the canMove state
            }

            if (!moved) {
                // Additional logic if the zombie cannot move
                // e.g., call a defeated function if it reaches the end
            }
        }

        // Remove all dead zombies
        zombieOnTile.removeAll(zombiesToRemove);

        // Remove all dead plants
        for (Tile[] tiles : tile) {
            for (Tile t : tiles) {
                for (Character owner : new ArrayList<>(t.getOwners())) {
                    if (owner instanceof Plant && owner.getHealth() <= 0) {
                        t.removeOwner(owner);
                    }
                }
            }
        }
    }

    private boolean processTileForZombie(Tile tile, Zombie zombie) {
        for (Character owner : tile.getOwners()) {
            if (owner instanceof Plant) {
                Plant plant = (Plant) owner;
                if (plant.getHealth() > 0 && zombie.canAction()) {
                    zombie.action();
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
    }

    public void placeZombie(Zombie z, int y) {
        this.zombieOnTile.add(z);
        this.zombiesByY.get(y).add(z);
        int x = z.getXChar();
        tile[x][y].addOwner(z);
    }

    public void placePlant(Plant p, int x, int y) {
        Tile targetTile = getTile(x, y);
        boolean containsLilypad = false;
        Plant lilypad = null;
        for (Character owner : targetTile.getOwners()) {
            if (owner.getName().equals("Lilypad")) {
                containsLilypad = true;
                lilypad = (Plant) owner;
                break;
            }
        }

        if (targetTile.getOwners().isEmpty() || containsLilypad) {
            if (containsLilypad) {
                p.setHealth(p.getHealth() + lilypad.getHealth());
            }

            targetTile.addOwner(p);
            p.setXChar(x);
            p.setYChar(y);
            this.plantOnTile.add(p);
        } else {
            System.out.println("Cannot place the plant on a tile that already contains a plant.");
        }
    }
}
