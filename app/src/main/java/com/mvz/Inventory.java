package com.mvz;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.mvz.exceptionhandling.InvalidInputException;
import com.mvz.plants.*;

public class Inventory {
    private static Inventory instance;
    private List<Plant> plants;

    private Inventory() {
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
    }

    public static Inventory getInstance() {
        if (instance == null) {
            instance = new Inventory();
        }
        return instance;
    }

    public void setPlants(List<Plant> plants) {
        this.plants = plants;
    }
    
    public List<Plant> getPlants() {
        return plants;
    }
    
    public void swapPlants(Scanner sc) {
        int x = 0; int y = 0;

        printInventory();

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

    public void printInventory() {
        System.out.println("\nInventory:");
        for (int i=0; i<plants.size(); i++) {
            System.out.printf("%d. %s\n", i+1, plants.get(i).name);
        }
    }
}