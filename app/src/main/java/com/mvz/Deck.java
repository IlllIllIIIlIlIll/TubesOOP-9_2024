package com.mvz;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Deck {
    private static Deck instance;
    private List<Plant> plants;

    public Deck() {
        plants = new ArrayList<>();
    }

    public static Deck getInstance() {
        if (instance == null) {
            instance = new Deck();
        }
        return instance;
    }

    public void Menu() {

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
            System.out.println("0. Next");
            
            System.out.println("\n");
            System.out.println("Masukkan nomor opsi yang dipilih");
            String input = sc.nextLine().trim();
    
            int x=0;
            
            if (input.matches("\\d+")) {
                x = Integer.parseInt(input);
                if (x==1) inventory.printInventory();
                else if (x==2) inventory.swapPlants();
                else if (x==3) printDeck();
                else if (x==4) setPlant();
                else if (x==5) deletePlant();
                else if (x==6) swapPlants();
                else if (x==0) validInput = true;
                else System.out.println("maaf, input kamu belum valid. silakan masukkan angka sesuai dengan nomor pada opsi pilihanmu:D");
            }
        }
    }

    public List<Plant> getPlants() {
        return plants;
    }

// pastikan kl listnya blm penuh sblm pake methodnya
    public void addPlant(Plant plant) {
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
    public void setPlant() {
        Inventory inventory = Inventory.getInstance();

        Scanner sc = new Scanner(System.in);
        boolean validInput = false;
    
        while (!validInput) {
            System.out.println("\n");
            inventory.printInventory();

            System.out.println();
            if (plants.isEmpty()) System.out.println("Deck kamu masih kosong!");
            else printDeck();

            System.out.println("\nMasukkan indeks tanaman yang ingin ditambahkan");
            String input = sc.nextLine().trim();
    
            int x;
            
            if (input.matches("\\d+")) {
                x = Integer.parseInt(input);
                if (x > 0 && x <= inventory.getPlants().size()){
                    if (!hasAddedPlant(inventory.getPlants().get(x-1))) {
                        addPlant(inventory.getPlants().get(x-1));
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

    // public void menanam() {
    //     Scanner sc = new Scanner(System.in);
    //     boolean validInput = false;
    
    //     while (!validInput) {
    //         printDeck();
    //         System.out.println("Masukkan indeks tanaman yang ingin ditanam");
    //         String input = sc.nextLine().trim(); // Dapatkan input dan hilangkan spasi di awal dan akhir
    
    //         int x=0;
    //         // Periksa apakah input hanya terdiri dari digit
    //         if (input.matches("\\d+")) {
    //             x = Integer.parseInt(input);
    //             if (x < plants.size()) {

    //             }
    //             else System.out.printf("Indeks harus kurang dari %d. Harap masukkan angka antara 0 dan %d.", plants.size(), plants.size()-1);
    //         } else {
    //             System.out.println("Format input tidak valid. Harap masukkan hanya satu angka.");
    //         }
    //     }
    //     sc.close(); // Tutup Scanner setelah penggunaan        
    // }

    public void printDeck() {
        System.out.println("Deck:");
        for (int i=0; i<plants.size(); i++) {
            System.out.printf("%d. %s\n", i+1, plants.get(i).name);
        }
    }

    public static void main(String[] args) {
        Deck deck = Deck.getInstance();
        deck.setPlant();
        deck.setPlant();
        deck.setPlant();
        deck.swapPlants();
        deck.deletePlant();
        deck.printDeck();
    }



}