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
            System.out.println("\nEnter the indices of two plants you want to swap in the format 'x y'");

            String input = sc.nextLine();
            String[] words = input.split("\\s+");

            try {
                if (input.equals("0")) {
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
                            System.out.printf("The positions of %s and %s in inventory have been successfully swapped!\n", plants.get(y-1).name, plants.get(x-1).name);
                            Deck.displayMenu();
                            break;
                        } else {
                            throw new InvalidInputException("The value of x and y have to be different!");
                        }
                    } else {
                        throw new InvalidInputException(String.format("Indices is out of range the deck! x and y must be an integer inclusively between 1 and %d.", plants.size()));
                    }
                } else {
                    throw new NumberFormatException();
                }
            } catch (InvalidInputException e) {
                System.out.println("INVALID INPUT: " + e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("Please enter two integers (x y) separated by a space!\nInput the number '0' if you wanna return to the game menu.");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void printPlant() {
        // better user experience
        System.out.print("\033[H\033[2J");
        System.out.flush();

        System.out.println("Plant list:");
        for (int i=0; i<plants.size(); i++) {
            System.out.printf("%d. %s\n", i+1, plants.get(i).name);
        }
        System.out.println();
    }

    public void printZombie() {
        // better user experience
        System.out.print("\033[H\033[2J");
        System.out.flush();
        
        System.out.println("Zombie list:");
        for (int i=0; i<zombies.size(); i++) {
            System.out.printf("%d. %s\n", i+1, zombies.get(i).name);
        }
        System.out.println();
    }
}