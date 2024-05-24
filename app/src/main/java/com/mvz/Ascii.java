package com.mvz;

public class Ascii {
    // ANSI escape codes for coloring
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_CYAN = "\u001B[36m";

    public static void main(String[] args) {
        mainPrint();
        startmenuPrint();
        gameMenuPrint();
        pauseMenuPrint();
        exitMenuPrint();
        endGameMenuPrint();
    }

    public static void mainPrint() {
        String[] art = {
            "  __  __               ____   _   _      _     U _____ u  _         __     __   ____           _         _       _         _       ____       _      _   _     ",
            "U|' \\/ '|u   ___    U /\"___| |'| |'| U  /\"\\  u \\| ___\"|/ |\"|        \\ \\   /\"/u / __\"| u       |\"|    U  /\"\\  u  |\"|    U  /\"\\  u U|  _\"\\ uU  /\"\\  u | \\ |\"|    ",
            "\\| |\\/| |/  |\"_|   \\ | | u  /| |_| |\\ \\/ _ \\/   |  _|\" U | | u       \\ \\ / // <\\___ \\/      U | | u   \\/ _ \\/ U | | u   \\/ _ \\/  \\| |_) |/ \\/ _ \\/ <|  \\| |>   ",
            " | |  | |    | |     | |/__ U|  _  |u / ___ \\   | |___  \\| |/__      /\\ V /_,-.u___) |       \\| |/__  / ___ \\  \\| |/__  / ___ \\   |  __/   / ___ \\ U| |\\  |u   ",
            " |_|  |_|  U/| |\\u    \\____| |_| |_| /_/   \\_\\  |_____|  |_____|    U  \\_/-(_/ |____/>>       |_____|/_/   \\_\\  |_____|/_/   \\_\\  |_|     /_/   \\_\\ |_| \\_|    ",
            "<<,-,,-..-,_|___|_,-._// \\\\  //   \\\\  \\\\    >>  <<   >>  //  \\\\       //        )(  (__)      //  \\\\  \\\\    >>  //  \\\\  \\\\    >>  ||>>_    \\\\    >> ||   \\\\,-. ",
            " (./  \\.)\\_)-' '-(_/(__)(__)(_\" )(\"_)(__)  (__)(__) (__)(_\" )(\")     (__)      (__)          (_\")(\"_)(__)  (__)(_\" )(\")(__)  (__)(__)__)  (__)  (__)(_\" )  (_/   "
        };

        // Adding an initial delay of 3 seconds before starting to print the ASCII art
        try {
            Thread.sleep(3000); // 3000 milliseconds delay
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Printing the main ASCII art with color and additional decoration
        for (String line : art) {
            // Adding a vine decoration at the beginning of each line
            System.out.println(ANSI_GREEN + ANSI_RESET + ANSI_CYAN + line + ANSI_RESET);
            // Adding a delay to create a loading effect
            try {
                Thread.sleep(250); // 250 milliseconds delay
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void startmenuPrint() {
        String[] art = {
            " ________________________________________________________________________________________________________________________________________________________________________ ",
            "|                                                                                                                                                                        |",
            "|                                                                                START MENU                                                                              |",
            "|________________________________________________________________________________________________________________________________________________________________________|",
            "|                                                               |                                         |                                                              |",
            "|                        List of Plants (3)                     |                 Help (2)                |                     List of Zombies (4)                      |",
            "|                                                               |                                         |                                                              |",
            "|---------------------------------------------------------------|-----------------------------------------|--------------------------------------------------------------|",
            "|                          Start (1)                                                  |                                             Close (5)                            |",
            "|________________________________________________________________________________________________________________________________________________________________________|"
        };

        // Adding a delay before starting to print the ASCII art
        try {
            Thread.sleep(50); // 2000 milliseconds delay
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Printing the start menu ASCII art with color and additional decoration
        for (String line : art) {
            System.out.println(ANSI_CYAN + line + ANSI_RESET);
            // Adding a delay to create a loading effect
            try {
                Thread.sleep(50); // 250 milliseconds delay
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

    }

    public static void gameMenuPrint() {
        String[] menuArt = {
            " ____________________________________________________________________________________________________________ ",
            "|                                              GAME MENU                                                     |",
            "|____________________________________________________________________________________________________________|",
            "|                   |                              |                               |                         |",
            "| Start the Game (0)| Display Inventory (1)        | Swap Plants Position          |                         |",
            "|                   |                              |   in Inventory (2)            |  Display Deck (3)       |",
            "|___________________|______________________________|_______________________________|                         |",
            "|                   |                              |                               |                         |",
            "| Add a Plant to    | Remove a Plant from Deck (5) | Swap Plants Position          |                         |",
            "|      Deck (4)     |                              |    in Deck (6)                |                         |",
            "|___________________|______________________________|_______________________________|_________________________|"
        };

        // Adding a delay before starting to print the ASCII art
        try {
            Thread.sleep(2000); // 2000 milliseconds delay
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Printing the game menu ASCII art with color and additional decoration
        for (String line : menuArt) {
            System.out.println(ANSI_CYAN + line + ANSI_RESET);
            // Adding a delay to create a loading effect
            try {
                Thread.sleep(50); // 250 milliseconds delay
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

    }

    public static void pauseMenuPrint() {
        String[] pauseArt = {
            " __________________________________________ ",
            "|                                          |",
            "|                PAUSE MENU                |",
            "|__________________________________________|",
            "|                  |                       |",
            "| Continue Game (1)|     Exit Game (2)     |",
            "|                  |                       |",
            "|__________________|_______________________|"
        };

        // Adding a delay before starting to print the ASCII art
        try {
            Thread.sleep(2000); // 2000 milliseconds delay
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Printing the pause menu ASCII art with color and additional decoration
        for (String line : pauseArt) {
            System.out.println(ANSI_CYAN + line + ANSI_RESET);
            // Adding a delay to create a loading effect
            try {
                Thread.sleep(50); // 250 milliseconds delay
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void exitMenuPrint() {
        String[] exitArt = {
            " ________________________________________________________________ ",
            "|                                                                |",
            "|                           EXIT MENU                            |",
            "|________________________________________________________________|",
            "|                   |                         |                  |",
            "|   Save Game (1)   | Quit Without Saving (2) | Return to Pause  |",
            "|                   |                         |      Menu (3)    |",
            "|___________________|_________________________|__________________|"
        };

        // Adding a delay before starting to print the ASCII art
        try {
            Thread.sleep(2000); // 2000 milliseconds delay
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Printing the exit menu ASCII art with color and additional decoration
        for (String line : exitArt) {
            System.out.println(ANSI_CYAN + line + ANSI_RESET);
            // Adding a delay to create a loading effect
            try {
                Thread.sleep(50); // 250 milliseconds delay
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

    }

    public static void endGameMenuPrint() {
        String[] endGameArt = {
            " __________________________________________________________ ",
            "|                                                          |",
            "|                       END GAME MENU                      |",
            "|__________________________________________________________|",
            "|                                                          |",
            "|                    Back to Main Menu (1)                 |",
            "|                                                          |",
            "|__________________________________________________________|"
        };

        // Adding a delay before starting to print the ASCII art
        try {
            Thread.sleep(2000); // 2000 milliseconds delay
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Printing the end game menu ASCII art with color and additional decoration
        for (String line : endGameArt) {
            System.out.println(ANSI_CYAN + line + ANSI_RESET);
            // Adding a delay to create a loading effect
            try {
                Thread.sleep(250); // 250 milliseconds delay
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

    }

}
