package com.mvz;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.mvz.exceptionhandling.InvalidInputException;
import com.mvz.plants.LandPlantFactory;
import com.mvz.plants.WaterPlantFactory;

public class Deck {
    private List<Plant> plants; 

    public Deck() {
        plants = new ArrayList<>();
    }

    public void deckMenu(Scanner sc){
        Inventory inventory = Inventory.getInstance();

        displayMenu();
    
        boolean validInput = false;
        
        while (!validInput) {
            System.out.printf("\nEnter your option: ");

            String input = sc.nextLine().trim();

            try {
                if (input.equals("0")) {
                    validInput = continueToTheGame();
                }
                else if (input.equals("")) {
                    displayMenu();
                }
                else if (input.matches("\\d+")) {
                    int x = Integer.parseInt(input);
                    switch (x) {
                        case 1:
                            inventory.printInventory();
                            displayMenu();
                            break;
                        case 2:
                            inventory.swapPlants(sc);
                            break;
                        case 3:
                            printDeck();
                            displayMenu();
                            break;
                        case 4:
                            addPlant(sc);
                            break;
                        case 5:
                            deletePlant(sc);
                            break;
                        case 6:
                            swapPlants(sc);
                            break;
                        default:
                            throw new NumberFormatException("Invalid option. Please enter a valid number according to the game menu.");
                    }
                }
                else {
                    throw new InvalidInputException("Please enter a number between 0 and 6.");
                }
            } catch (InvalidInputException e) {
                System.out.println("INVALID INPUT: " + e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
            }
            
        }
        // better user experience
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    // to display game menu before the game starts
    public static void displayMenu() {
        System.out.println("\n===============GAME MENU===============");
        System.out.println("0. Start the Game");
        System.out.println("1. Display Inventory");
        System.out.println("2. Swap Plants Position in Inventory");    
        System.out.println("3. Display Deck");
        System.out.println("4. Add a Plant to Deck");
        System.out.println("5. Remove a Plant from Deck");
        System.out.println("6. Swap Plants Position in Deck");
    }

    // getter plants
    public List<Plant> getPlants() {
        return plants;
    }

    // setter plants
    public void setPlant(Plant plant) {
        plants.add(plants.size(), plant);
    }
    
    // check if deck contains the plant
    public boolean hasAddedPlant(Plant plant) {
        boolean hasAdded = false;
        for (Plant tumbuhan : plants) {
            if (tumbuhan.getClass().equals(plant.getClass())) {
                hasAdded = true; 
                break;
            }
        }
        return hasAdded;
    }

    // swap positions between two plants
    public void swapPlants(Scanner sc) throws InvalidInputException {    
        int x = 0; int y = 0;

        printDeck();

        if (plants.size() < 2) throw new InvalidInputException(String.format("The number of plants in your deck is still not enough to do swap plants. Please add %d more plants before swapping.", plants.size()-2));
        else {
            while (true) {
                System.out.println("\nEnter the indices of two plants you want to swap in the format 'x y'");

                String input = sc.nextLine();
                String[] words = input.split("\\s+");

                if (input.equals("0")) {
                    displayMenu();
                    break;
                } else if (words.length == 2) {
                    x = Integer.parseInt(words[0]);
                    y = Integer.parseInt(words[1]);
                    if (x > 0 && x <= plants.size() && y > 0 && y <= plants.size()) {
                        if (x != y) {
                            Plant temp = plants.get(y-1);
                            plants.set(y-1, plants.get(x-1));
                            plants.set(x-1, temp);
                            System.out.printf("The positions of %s and %s have been successfully swapped!\n", plants.get(y-1).name, plants.get(x-1).name);
                            displayMenu();
                            break;
                        } else {
                            throw new InvalidInputException("The value of x and y have to be different!");
                        }
                    } else {
                        throw new InvalidInputException(String.format("Indices is out of range the deck! x and y must be an integer inclusively between 1 and %d.", plants.size()));
                    }
                } else {
                    throw new InvalidInputException("Please enter two integers (x y) separated by a space!\nInput the number '0' if you wanna return to the game menu.");
                }
            }
        }
    }

    public void addPlant(Scanner sc) {
        Inventory inventory = Inventory.getInstance();

        while (true) {
            if (plants.size() == 6) {
                System.out.println("Your deck is super duper full! Can't add any plant anymore.");
                displayMenu();
                break;
            }

            inventory.printInventory();

            if (!plants.isEmpty()) {
                printDeck();
            } else {
                System.out.println("Your deck is still empty.");
            }

            System.out.println("\nEnter the index of plant you wanna add");
            String input = sc.nextLine().trim();
    
            try {
                if (input.equals("0")) {
                    displayMenu();
                    break;
                }
                else if (input.matches("\\d+")) {
                    int x = Integer.parseInt(input);
                    if (x > 0 && x <= inventory.getPlants().size()){
                        if (!hasAddedPlant(inventory.getPlants().get(x-1))) {
                            setPlant(inventory.getPlants().get(x-1));
                            System.out.printf("%s was successfully added to the deck!\n", inventory.getPlants().get(x-1).name);
                            displayMenu();
                            break;
                        }   else {
                            System.out.printf("The plant failed to be added. %s is already in the deck! \nInput the number '0' if you wanna return to the game menu.", inventory.getPlants().get(x-1).name);
                        }   
                    } else {
                        throw new InvalidInputException("Indeks out of range inventory. Please enter a number between 1 and 10. \nInput the number '0' if you wanna return to the game menu.");
                    }
                }   else {
                        throw new InvalidInputException("Please enter an index based on the order of plants in inventory! \nInput the number '0' if you wanna return to the game menu.");
                }
            }   catch (InvalidInputException e) {
                System.out.println("\nINVALID INPUT: " + e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("\n"+ e.getMessage());
            }

        }
    }

    // sebelum masuk ke method, pastikan dulu kl decknya ga kosong
    public void deletePlant(Scanner sc) {
        boolean validInput = false;
    
        while (!validInput) {
            System.out.println("\n");
            printDeck();

            System.out.println("Masukkan indeks tanaman yang ingin dihapus");
            String input = sc.nextLine().trim();
    
            int x=0;
            
            if (input.matches("\\d+")) {
                x = Integer.parseInt(input);
                if (x <= plants.size() && x>0) {
                    System.out.printf("Tanaman %s berhasil dihapus dari deck!", plants.get(x-1).name);
                    plants.remove(x-1);
                    validInput = true;
                }   else System.out.printf("Indeks out of range deck. Harap masukkan indeks antara 1 dan %d.", plants.size());
            } else {
                System.out.println("Format input tidak valid. Harap masukkan hanya sebuah angka pada rentang indeks tanaman di deck.");
            }
        }
    }

    public void printDeck() {
        if (plants.size() == 0) System.out.println("Deck kamu masih kosong!");
        else {
            System.out.println("Your current deck:");
            for (int i=0; i<plants.size(); i++) {
                System.out.printf("%d. %s\n", i+1, plants.get(i).name);
            }
        }
    }

    public boolean continueToTheGame() {
        if (plants.size() == 6) {
            return true;
        }
        else {
            System.out.printf(String.format("Your deck currently contains %d plants. Add %d plant(s) to get started to the game.\n", plants.size(), 6-plants.size()));
            return false;
        }
    }

    public Plant createThePlant(String input, Tile tile) {
        Plant plantToPlant = null;
        for (Plant tumbuhan : getPlants()) {
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

}