public class Ascii {
    // ANSI escape codes for coloring
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_CYAN = "\u001B[36m";

    public static void main(String[] args) {
        String[] art = {
            "  __  __               ____   _   _      _     U _____ u  _         __     __   ____           _         _       _         _       ____       _      _   _     ",
            "U|' \\/ '|u   ___    U /\"___| |'| |'| U  /\"\\  u \\| ___\"|/ |\"|        \\ \\   /\"/u / __\"| u       |\"|    U  /\"\\  u  |\"|    U  /\"\\  u U|  _\"\\ uU  /\"\\  u | \\ |\"|    ",
            "\\| |\\/| |/  |\"_|   \\| | u  /| |_| |\\ \\/ _ \\/   |  _|\" U | | u       \\ \\ / // <\\___ \\/      U | | u   \\/ _ \\/ U | | u   \\/ _ \\/  \\| |_) |/ \\/ _ \\/ <|  \\| |>   ",
            " | |  | |    | |     | |/__ U|  _  |u / ___ \\   | |___  \\| |/__      /\\ V /_,-.u___) |       \\| |/__  / ___ \\  \\| |/__  / ___ \\   |  __/   / ___ \\ U| |\\  |u   ",
            " |_|  |_|  U/| |\\u    \\____| |_| |_| /_/   \\_\\  |_____|  |_____|    U  \\_/-(_/ |____/>>       |_____|/_/   \\_\\  |_____|/_/   \\_\\  |_|     /_/   \\_\\ |_| \\_|    ",
            "<<,-,,-..-,_|___|_,-._// \\\\  //   \\\\  \\\\    >>  <<   >>  //  \\\\       //        )(  (__)      //  \\\\  \\\\    >>  //  \\\\  \\\\    >>  ||>>_    \\\\    >> ||   \\\\,-. ",
            " (./  \\.)\\_)-' '-(_/(__)(__)(_\" )(\"_)(__)  (__)(__) (__)(_\" )(\")     (__)      (__)          (_\")(\"_)(__)  (__)(_\" )(\")(__)  (__)(__)__)  (__)  (__)(_\" )  (_/   "
        };

        // Printing the main ASCII art with color and additional decoration
        for (String line : art) {
            // Adding a vine decoration at the beginning of each line
            System.out.println(ANSI_GREEN + ANSI_RESET + ANSI_CYAN + line + ANSI_RESET);
        }
    }
}
