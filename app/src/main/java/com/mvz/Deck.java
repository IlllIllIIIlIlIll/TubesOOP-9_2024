package com.mvz;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.mvz.exceptionhandling.InvalidInputException;

public class Deck {
    private List<Plant> plants; 
    private Inventory inventory;

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_RED = "\u001B[31m";

    public Deck() {
        plants = new ArrayList<>();
        inventory = new Inventory();
    }

    public void deckMenu(Scanner sc){
        displayMenu();
    
        boolean validInput = false;
        
        while (!validInput) {
            //System.out.printf(ANSI_CYAN + "\nEnter your option: " + ANSI_RESET);

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
                            // better user experience
                            System.out.print("\033[H\033[2J");
                            System.out.flush();
                            inventory.printPlant();
                            displayMenu();
                            break;
                        case 2:
                            // better user experience
                            System.out.print("\033[H\033[2J");
                            System.out.flush();
                            inventory.printPlant();
                            System.out.println(ANSI_CYAN + "0. Back" + ANSI_RESET);
                            inventory.swapPlants(sc);
                            break;
                        case 3:
                            // better user experience
                            System.out.print("\033[H\033[2J");
                            System.out.flush();
                            printDeck();
                            displayMenu();
                            break;
                        case 4:
                            // better user experience
                            System.out.print("\033[H\033[2J");
                            System.out.flush();
                            addPlant(sc);
                            break;
                        case 5:
                            deletePlant(sc);
                            break;
                        case 6:
                            printDeck();
                            swapPlants(sc);
                            break;
                        default:
                            throw new NumberFormatException();
                    }
                }
                else {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                // better user experience
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println(ANSI_RED + "Invalid option. Please enter a valid number according to the game menu." + ANSI_RESET);
            } catch (Exception e) {
                // better user experience
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println(ANSI_CYAN + e.getMessage() + ANSI_RESET);
            }
            
        }
        // better user experience
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    // to display game menu before the game starts
    public static void displayMenu() {
        Ascii.gameMenuPrint();
        System.out.println(ANSI_CYAN + "Choose your option: " + ANSI_RESET);
        
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
    public void swapPlants(Scanner sc) {
        // better user experience
        System.out.print("\033[H\033[2J");
        System.out.flush();
    
        int x = 0;
        int y = 0;
    
        printDeck();
    
        try {
            if (plants.size() < 2) {
                // better user experience
                System.out.print("\033[H\033[2J");
                System.out.flush();
                throw new InvalidInputException(String.format(ANSI_RED + "The number of plants in your deck is still not enough to do swap plants. Please add %d more plants before swapping.", 2-plants.size()) + ANSI_RESET);
            } else {
                while (true) {
                    System.out.println(ANSI_RED + "\nEnter the indices of two plants you want to swap in the format 'x y'" + ANSI_RESET);
    
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
                                Plant temp = plants.get(y - 1);
                                plants.set(y - 1, plants.get(x - 1));
                                plants.set(x - 1, temp);
                                System.out.printf(ANSI_RED + "The positions of %s and %s have been successfully swapped!\n", plants.get(y - 1).name, plants.get(x - 1).name + ANSI_RESET);
                                // better user experience
                                System.out.print("\033[H\033[2J");
                                System.out.flush();
                                displayMenu();
                                break;
                            } else {
                                // better user experience
                                System.out.print("\033[H\033[2J");
                                System.out.flush();
                                inventory.printPlant();
                                System.out.println(ANSI_CYAN + "0. Back" + ANSI_RESET);
                                throw new InvalidInputException(ANSI_RED + "The value of x and y have to be different!" + ANSI_RESET);
                            }
                        } else {
                            // better user experience
                            System.out.print("\033[H\033[2J");
                            System.out.flush();
                            inventory.printPlant();
                            System.out.println(ANSI_CYAN + "0. Back" + ANSI_RESET);
                            throw new InvalidInputException(String.format(ANSI_RED + "Indices is out of range the deck! x and y must be an integer inclusively between 1 and %d.", plants.size() + ANSI_RESET));
                        }
                    } else {
                        // better user experience
                        System.out.print("\033[H\033[2J");
                        System.out.flush();
                        inventory.printPlant();
                        System.out.println(ANSI_CYAN + "0. Back" + ANSI_RESET);
                        throw new InvalidInputException(ANSI_RED + "Please enter two integers (x y) separated by a space!" + ANSI_RESET);
                    }
                }
            }
        } catch (InvalidInputException e) {
            // better user experience
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println(ANSI_CYAN + e.getMessage() + ANSI_RESET);
            displayMenu();
        }
    }
    

    public void addPlant(Scanner sc) {
        if (plants.size() == 6) {
            // better user experience
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println(ANSI_RED + "Your deck is super duper full! Can't add any plant anymore." + ANSI_RESET);
            displayMenu();
        } else {
            while (true) {
                inventory.printPlant();
                System.out.println(ANSI_CYAN + "0. Back" + ANSI_RESET);
                printDeck();
                System.out.println(ANSI_CYAN + "\nEnter the index of plant in inventory that you wanna add" + ANSI_RESET);

                String input = sc.nextLine().trim();
        
                try {
                    if (input.equals("0")) {
                        // better user experience
                        System.out.print("\033[H\033[2J");
                        System.out.flush();
                        displayMenu();
                        break;
                    } else if (input.matches("\\d+")) {
                        int x = Integer.parseInt(input);
                        if (x > 0 && x <= inventory.getPlants().size()){
                            if (!hasAddedPlant(inventory.getPlants().get(x-1))) {
                                // better user experience
                                System.out.print("\033[H\033[2J");
                                System.out.flush();

                                setPlant(inventory.getPlants().get(x-1));
                                System.out.printf(ANSI_CYAN + "%s was successfully added to the deck!\n", inventory.getPlants().get(x-1).name + ANSI_RESET);
                                displayMenu();
                                break;
                            } else {
                                // better user experience
                                System.out.print("\033[H\033[2J");
                                System.out.flush();
                                throw new InvalidInputException(String.format(ANSI_RED + "The plant failed to be added. %s is already in the deck!\n", inventory.getPlants().get(x-1).name + ANSI_RESET));
                            }   
                        } else {
                            // better user experience
                            System.out.print("\033[H\033[2J");
                            System.out.flush();
                            throw new InvalidInputException(ANSI_RED + "The index is out of range inventory. Please enter a number between 1 and 10. \n" + ANSI_RESET);
                        }
                    } else {
                        // better user experience
                        System.out.print("\033[H\033[2J");
                        System.out.flush();
                        throw new NumberFormatException();
                    }
                } catch (InvalidInputException e) {
                    // better user experience
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    System.out.println(ANSI_RED + "\nINVALID INPUT: " + e.getMessage() + ANSI_RESET);
                } catch (NumberFormatException e) {
                    // better user experience
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    System.out.println(ANSI_RED + "Please enter the index based on the order of plants in inventory! \n" + ANSI_RESET);
                } catch (Exception e) {
                    // better user experience
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    System.out.println(ANSI_CYAN + e.getMessage() + ANSI_RESET);
                }
            }
        }
    }

    // sebelum masuk ke method, pastikan dulu kl decknya ga kosong
    public void deletePlant(Scanner sc) {    
        if (plants.isEmpty()) {
            displayMenu();
        } else {
            while (true) {
                // better user experience
                System.out.print("\033[H\033[2J");
                System.out.flush();
                printDeck();

                System.out.println(ANSI_CYAN + "\nEnter the index of plant in deck that you wanna remove" + ANSI_RESET);
                String input = sc.nextLine().trim();

                try {
                    if (input.equals("0")) {
                        // better user experience
                        System.out.print("\033[H\033[2J");
                        System.out.flush();
                        displayMenu();
                        break;
                    } else if (input.matches("\\d+")) {
                        int x = Integer.parseInt(input);
                        if (x <= plants.size() && x>0) {
                            // better user experience
                            System.out.print("\033[H\033[2J");
                            System.out.flush();
                            System.out.printf(ANSI_CYAN + "%s was successfully removed from the deck!\n", plants.get(x-1).name + ANSI_RESET);
                            plants.remove(x-1);
                            printDeck();
                            displayMenu();
                            break;
                        } else {
                            // better user experience
                            System.out.print("\033[H\033[2J");
                            System.out.flush();
                            throw new InvalidInputException(String.format(ANSI_RED + "The index is out of range deck. Please enter a number between 1 and %d. \n", plants.size() + ANSI_RESET));
                        }
                    } else {
                        // better user experience
                        System.out.print("\033[H\033[2J");
                        System.out.flush();
                        throw new NumberFormatException();
                    }
                } catch (InvalidInputException e) {
                    // better user experience
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    System.out.println(ANSI_CYAN + e.getMessage() + ANSI_RESET);
                } catch (NumberFormatException e) {
                    // better user experience
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    System.out.println(ANSI_RED + "Please enter the index based on the order of plants in deck! \n" + ANSI_RESET);
                } catch (Exception e) {
                    // better user experience
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    System.out.println(ANSI_CYAN + e.getMessage() + ANSI_RESET);
                }
            }
        }
    }

    public void printDeck() {

        if (plants.size() == 0) {
            System.out.println(ANSI_RED + "Your deck is still empty." + ANSI_RESET);
        }
        else {
            System.out.println(ANSI_CYAN + "Your current deck:" + ANSI_RESET);
            for (int i=0; i<plants.size(); i++) {
                System.out.printf(ANSI_CYAN + "%d. %s\n", i+1, plants.get(i).name + ANSI_RESET);
            }
            System.out.println(ANSI_CYAN + "0. Back" + ANSI_RESET);
        }
    }

    public boolean continueToTheGame() {
        if (plants.size() == 6) {
            // better user experience
            System.out.print("\033[H\033[2J");
            System.out.flush();
            return true;
        }
        else {
            // better user experience
            System.out.print("\033[H\033[2J");
            System.out.flush();

            System.out.printf(String.format(ANSI_RED + "Your deck currently contains %d plants. Add %d more plant(s) to get started to the game.\n", plants.size(), 6-plants.size() + ANSI_RESET));
            displayMenu();
            return false;
        }
    }

}