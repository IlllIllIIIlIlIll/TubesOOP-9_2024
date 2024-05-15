package com.mvz;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
    
    public void swapPlants() {
        Scanner sc = new Scanner(System.in);
        boolean validInput = false;
    
        int x = 0;
        int y = 0;
    
        while (!validInput) {
            System.out.println("Masukkan dua indeks tanaman yang ingin di-swap dengan format 'x y'");
            if (sc.hasNextInt()) {
                x = sc.nextInt();
                if (x < 10 && sc.hasNextInt()) {
                    y = sc.nextInt();
                    if (y < 10 && x != y) validInput = true;
                    else System.out.println("nilai x dan y harus berbeda!");
                } else {
                    System.out.println("Format input tidak valid. Harap masukkan dua angka yang dipisahkan oleh spasi.");
                    sc.nextLine(); // Buang baris yang tidak valid
                }
            } else {
                System.out.println("Format input tidak valid. Harap masukkan dua angka yang dipisahkan oleh spasi.");
                sc.nextLine(); // Buang baris yang tidak valid
            }
        }
    
        sc.close(); // Tutup Scanner setelah penggunaan
    
        Plant temp = plants.get(y);
        plants.set(y, plants.get(x));
        plants.set(x, temp);
    }

    public void printInventory() {
        System.out.println("Inventory:");
        for (int i=0; i<plants.size(); i++) {
            System.out.printf("%d. %s\n", i+1, plants.get(i).name);
        }
    }
}