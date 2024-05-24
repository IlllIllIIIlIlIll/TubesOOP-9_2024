package com.mvz.menu;

public class HelpCommand implements Command {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_CYAN = "\u001B[36m";

    @Override
    public void execute() {
        
        System.out.println(ANSI_CYAN + "This is the help menu. Here are some tips to play Game Michael vs. Lalapan!"+ ANSI_RESET);
        System.out.println(ANSI_CYAN + "Place and manage plants in your deck to prevent waves of zombies from reaching your house."+ ANSI_RESET);
        System.out.println(ANSI_CYAN + "Before the game starts, you need to customize and fulfill your deck."+ ANSI_RESET);
        System.out.println(ANSI_CYAN + "List of commands in deck menu: ");
        System.out.println(ANSI_CYAN + "0. Start the Game: You can start the game when your deck is already full (there's 6 type of plants in deck). "+ ANSI_RESET);
        System.out.println(ANSI_CYAN + "1. Display Inventory: To show list of plants and zombies in your inventory"+ ANSI_RESET);
        System.out.println(ANSI_CYAN + "2. Swap Plants Position in Inventory: To swap positions between two plants in the inventory."+ ANSI_RESET);    
        System.out.println(ANSI_CYAN + "Format: <index of plant1> <index of plant2>"+ ANSI_RESET);
        System.out.println(ANSI_CYAN + "3. Display Deck: To show list of plants in your deck"+ ANSI_RESET);
        System.out.println(ANSI_CYAN + "4. Add a Plant to Deck");
        System.out.println(ANSI_CYAN + "Format: Enter index of a plant that you want to add. Please make sure that the plant has not been added to the deck."+ ANSI_RESET);
        System.out.println(ANSI_CYAN + "5. Remove a Plant from Deck"+ ANSI_RESET);
        System.out.println(ANSI_CYAN + "Format: Enter index of a plant that you want to remove."+ ANSI_RESET);
        System.out.println(ANSI_CYAN + "6. Swap Plants Position in Deck"+ ANSI_RESET);
        System.out.println(ANSI_CYAN + "Format: <index of plant1> <index of plant2>"+ ANSI_RESET);

        System.out.println(ANSI_CYAN + "After the game starts, you can enter one of these commands."+ ANSI_RESET);
        System.out.println(ANSI_CYAN + "1. Place a plant to a tile"+ ANSI_RESET);
        System.out.println(ANSI_CYAN + "Format: tanam <plant name> <coordinate x> <coordinate y>"+ ANSI_RESET);
        System.out.println(ANSI_CYAN + "2. Remove all plants in a tile"+ ANSI_RESET);
        System.out.println(ANSI_CYAN + "Format: gali <coordinate x> <coordinate y>"+ ANSI_RESET);  
        System.out.println(ANSI_CYAN + "3. Pause the game"+ ANSI_RESET);
        System.out.println(ANSI_CYAN + "Format: pause"+ ANSI_RESET);
        System.out.println(ANSI_CYAN + "Lalapans win if all the zombies die"+ ANSI_RESET);
        System.out.println(ANSI_CYAN + "Michael wins if there's a zombie that successes breaking all defenses and reach the Lalapan's house."+ ANSI_RESET);      
    }
}