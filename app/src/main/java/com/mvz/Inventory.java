package com.mvz;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.mvz.exceptionhandling.InvalidInputException;
import com.mvz.plants.*;
import com.mvz.zombies.*;

public class Inventory {
    private List<Plant> plants;
    private List<Zombie> zombies;

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_RED = "\u001B[31m";

    public Inventory() { // Change constructor to public
        plants = new ArrayList<>();
        plants.add(new Cherrybomb());
        plants.add(new Jalapeno());
        plants.add(new Lilypad());
        plants.add(new Peashooter());
        plants.add(new Repeater());
        plants.add(new Snowpea());
        plants.add(new Squash());
        plants.add(new Sunflower());
        plants.add(new Tanglekelp());
        plants.add(new Wallnut());
        zombies = new ArrayList<>();
        zombies.add(new Buckethead());
        zombies.add(new Conehead());
        zombies.add(new Dolphinrider());
        zombies.add(new Duckytube());
        zombies.add(new Football());
        zombies.add(new Jackinthebox());
        zombies.add(new Koran());
        zombies.add(new Normal());
        zombies.add(new Polevaulting());
        zombies.add(new Ra());
    }

    public void setPlants(List<Plant> plants) {
        this.plants = plants;
    }
    
    public List<Plant> getPlants() {
        return plants;
    }
    
    public void swapPlants(Scanner sc) {
        int x = 0; int y = 0;
        while (true) {
            System.out.println(ANSI_CYAN + "\nEnter the indices of two plants you want to swap in the format 'x y'" + ANSI_RESET);

            String input = sc.nextLine();
            String[] words = input.split("\\s+");

            try {
                if (input.equals("0")) {
                    // better user experience
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    Deck.displayMenu();
                    break;
                } else if (words.length == 2) {
                    x = Integer.parseInt(words[0]);
                    y = Integer.parseInt(words[1]);
                    if (x > 0 && x <= plants.size() && y > 0 && y <= plants.size()) {
                        if (x != y) {
                            Plant temp = plants.get(y-1);
                            plants.set(y-1, plants.get(x-1));
                            plants.set(x-1, temp);
                            System.out.printf(ANSI_CYAN + "The positions of %s and %s in inventory have been successfully swapped!\n", plants.get(y-1).name, plants.get(x-1).name + ANSI_RESET);
                            // better user experience
                            System.out.print("\033[H\033[2J");
                            System.out.flush();
                            Deck.displayMenu();
                            break;
                        } else {
                            // better user experience
                            System.out.print("\033[H\033[2J");
                            System.out.flush();
                            printPlant();
                            System.out.println(ANSI_CYAN + "0. Back" + ANSI_RESET);
                            throw new InvalidInputException(ANSI_RED + "The value of x and y have to be different!" + ANSI_RESET);
                        }
                    } else {
                        // better user experience
                        System.out.print("\033[H\033[2J");
                        System.out.flush();
                        printPlant();
                        System.out.println(ANSI_CYAN + "0. Back" + ANSI_RESET);
                        throw new InvalidInputException(String.format(ANSI_RED + "Indices is out of range the deck! x and y must be an integer inclusively between 1 and %d.", plants.size() + ANSI_RESET));
                    }
                } else {
                    // better user experience
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    printPlant();
                    System.out.println(ANSI_CYAN + "0. Back" + ANSI_RESET);
                    throw new NumberFormatException();
                }
            } catch (InvalidInputException e) {
                // better user experience
                System.out.print("\033[H\033[2J");
                System.out.flush();
                printPlant();
                System.out.println(ANSI_CYAN + "0. Back" + ANSI_RESET);
                System.out.println(ANSI_RED + "INVALID INPUT: " + e.getMessage() + ANSI_RESET);
            } catch (NumberFormatException e) {
                // better user experience
                System.out.print("\033[H\033[2J");
                System.out.flush();
                printPlant();
                System.out.println(ANSI_CYAN + "0. Back" + ANSI_RESET);
                System.out.println(ANSI_RED + "Please enter two integers (x y) separated by a space!\n" + ANSI_RESET);
            } catch (Exception e) {
                // better user experience
                System.out.print("\033[H\033[2J");
                System.out.flush();
                printPlant();
                System.out.println(ANSI_CYAN + "0. Back" + ANSI_RESET);
                System.out.println(ANSI_CYAN + e.getMessage() + ANSI_RESET);
            }
        }
    }

    public void printPlant() {

        System.out.println(ANSI_CYAN + "Plant list:" + ANSI_RESET);
        for (int i=0; i<plants.size(); i++) {
            System.out.printf(ANSI_CYAN + "%d. %s\n", i+1, plants.get(i).name + ANSI_RESET);
        }
        System.out.println();
    }

    public void printZombie() {
        
        System.out.println(ANSI_CYAN + "Zombie list:" + ANSI_RESET);
        for (int i=0; i<zombies.size(); i++) {
            System.out.printf(ANSI_CYAN + "%d. %s\n", i+1, zombies.get(i).name + ANSI_RESET);
        }
        System.out.println();
    }
}