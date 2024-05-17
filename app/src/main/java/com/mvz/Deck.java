package com.mvz;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.mvz.plants.LandPlantFactory;
import com.mvz.plants.WaterPlantFactory;

public class Deck {
    private List<Plant> plants;

    public Deck() {
        plants = new ArrayList<>();
    }

    public void deckMenu() {
        Scanner sc = new Scanner(System.in);
        Inventory inventory = Inventory.getInstance();
        boolean validInput = false;
    
        while (!validInput) {
            System.out.println("pilih salah satu menu di antara opsi berikut untuk memodifikasi inventory/deck.");
            System.out.println("1. Tampilkan inventory");
            System.out.println("2. Tukar posisi tanaman di inventory");    
            System.out.println("3. Tampilkan deck");
            System.out.println("4. Tambahkan tanaman ke deck");
            System.out.println("5. Hapus tanaman dari deck");
            System.out.println("6. Tukar posisi tanaman di deck");
            System.out.println("0. Start the game");
            
            System.out.println("\n");
            System.out.println("Masukkan nomor opsi yang dipilih");
            String input = sc.nextLine().trim();
            
            if (input.matches("\\d+")) {
                int x = Integer.parseInt(input);
                if (x==1) inventory.printInventory();
                else if (x==2) inventory.swapPlants();
                else if (x==3) printDeck();
                else if (x==4) addPlant();
                else if (x==5) deletePlant();
                else if (x==6) swapPlants();
                else if (x==0) validInput = continueToTheGame();
            }   else {
                System.out.println("Format input tidak valid. Harap masukkan hanya sebuah angka sesuai opsi yang tersedia.");
            }
        }
    }

    public List<Plant> getPlants() {
        return plants;
    }

// pastikan kl listnya blm penuh sblm pake methodnya
    public void setPlant(Plant plant) {
        plants.add(plants.size(), plant);
    }
    
// cek plant nya udah ditambahin ato belum ke deck
    public boolean hasAddedPlant(Plant plant) {
        boolean hasAdded = false;
        for (Plant tumbuhan : plants) {
            if (tumbuhan.getName().equals(plant.getName())) hasAdded = true; 
        }
        return hasAdded;
    }

    // cek plant nya ada di deck ga
    public boolean isPlantInDeck(String plant) {
        boolean found = false;
        for (Plant tumbuhan : plants) {
            if (tumbuhan.getName().equals(plant)) found = true; 
        }
        return found;
    }

    // minimal ada 2 plant
    public void swapPlants() {
        Scanner sc = new Scanner(System.in);
        boolean validInput = false;
    
        int x = 0;
        int y = 0;
    
        while (!validInput) {
            System.out.println("\n");
            printDeck();
            System.out.println("\nMasukkan dua indeks tanaman yang ingin di-swap dengan format 'x y'");
            if (sc.hasNextInt()) {
                x = sc.nextInt();
                if (x <= plants.size() && x > 0) {
                    if (sc.hasNextInt()) {
                        y = sc.nextInt();
                        if (y <= plants.size() && y > 0) {
                            if (x != y) {
                                validInput = true;
                            } else {
                                System.out.println("Nilai x dan y harus berbeda!");
                            }
                        } else {
                            System.out.printf("Indeks kedua out of range deck. Masukkan nilai kedua indeks antara 1 dan %d (inklusif)\n", plants.size());
                        }
                    } else {
                        System.out.println("Format input tidak valid. Harap masukkan dua angka yang dipisahkan oleh spasi.");
                        sc.nextLine();
                    }
                } else {
                    System.out.printf("Indeks pertama out of range deck. Masukkan nilai kedua indeks antara 1 dan %d (inklusif)\n", plants.size());
                }
            } else {
                System.out.println("Format input tidak valid. Harap masukkan dua angka yang dipisahkan oleh spasi.");
                sc.nextLine();
            }
        }   
    
        Plant temp = plants.get(y-1);
        plants.set(y-1, plants.get(x-1));
        plants.set(x-1, temp);

        System.out.printf("Posisi dari tanaman %s dan %s berhasil ditukar!", plants.get(y-1).name, plants.get(x-1).name);
    }

    // pastiin deck belum full
    public void addPlant() {
        Inventory inventory = Inventory.getInstance();

        Scanner sc = new Scanner(System.in);
        boolean validInput = false;
    
        if (plants.size() == 6) {
            System.out.println("Deck kamu sudah penuh");
            validInput = true;}

        else {
            while (!validInput) {
                System.out.println("\n");
                inventory.printInventory();

                System.out.println();
                if (!plants.isEmpty()) {
                    System.out.println("Your current deck");
                    printDeck();
                }

                System.out.println("\nMasukkan indeks tanaman yang ingin ditambahkan");
                String input = sc.nextLine().trim();
        
                int x;
                
                if (input.matches("\\d+")) {
                    x = Integer.parseInt(input);
                    if (x > 0 && x <= inventory.getPlants().size()){
                        if (!hasAddedPlant(inventory.getPlants().get(x-1))) {
                            setPlant(inventory.getPlants().get(x-1));
                            System.out.printf("Tanaman %s berhasil ditambahkan!\n", inventory.getPlants().get(x-1).name);
                            validInput = true;
                        }   else {System.out.printf("Tanaman %s sudah ditambahkan ke dalam deck. Masukkan tanaman lainnya woi\n", inventory.getPlants().get(x-1).name);
                        }
                    }   else {System.out.println("Indeks out of range inventory. Harap masukkan angka antara 1 dan 10");
                    }
                }   else {
                    System.out.println("Format input tidak valid. Harap masukkan hanya sebuah angka pada rentang indeks tanaman di inventory.");
                }
            }
        }
    }

    // sebelum masuk ke method, pastikan dulu kl decknya ga kosong
    public void deletePlant() {
        Scanner sc = new Scanner(System.in);
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
            System.out.println("Deck:");
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
            System.out.println("Isi dulu decknya ampe full woy");
            return false;
        }
    }

    public Plant createThePlant(String input, Tile tile) {
        Plant plantToPlant = null;
        for (Plant tumbuhan : getPlants()) {
            if (tumbuhan.getName().equals(input)) {
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
        
    // BUAT TESTING
    public static void main(String[] args) {
        Deck deck = new Deck();
        deck.deckMenu();
    }
}