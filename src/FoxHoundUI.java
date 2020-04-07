import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.Objects;

/**
 * A utility class for the fox hound program.
 * 
 * It contains helper functions for all user interface related
 * functionality such as printing menus and displaying the game board.
 */
public class FoxHoundUI {

    /** Number of main menu entries. */
    private static final int MENU_ENTRIES = 4;
    /** Main menu display string. */
    private static final String MAIN_MENU =
        "\n1. Move\n2. Save Game\n3. Load Game\n4. Exit\n\nEnter 1 - 4:";

    /** Menu entry to select a move action. */
    public static final int MENU_MOVE = 1;
    public static final int MENU_SAVE_GAME = 2;
    public static final int MENU_LOAD_GAME = 3;
    /** Menu entry to terminate the program. */
    public static final int MENU_EXIT = 4;

    /**
     *
     * @param players = the current positions of the pieces
     * @param dimension = the board's dimension
     */
    public static void displayBoard(String[] players, int dimension) {
        //the dimension <= 9 is meant to make the ascii graphic more cleaner
        //create the above row alphabet
        {
            if (dimension <= 9) {
                System.out.print("   ");
                for (char i = 'A'; i < (65 + dimension); i++) {
                    System.out.print(i);
                }
            }
            //same as above code, but include more spaces because involve double digit
            else {
                System.out.print("    ");
                for (char i = 'A'; i < (65 + dimension); i++) {
                    System.out.print(i);
                }
            }
        }

        System.out.print("  \n");

        if (dimension <= 9) {
            for (int x = 1; x <= dimension; x++) {
                System.out.print("\n" + x + " ");
                for (char i = 'A'; i < (char) ('A' + dimension); i++) {
                    if (FoxHoundUtils.contain(players, String.valueOf(i) + x)) {
                        for (int j = 0; j < 5; j++) {
                            if (i == players[j].charAt(0) && x == Integer.parseInt(String.valueOf(players[j].charAt(1))) && j == 4) {
                                System.out.print(FoxHoundUtils.FOX_FIELD);
                            } else if (i == players[j].charAt(0) && x == Integer.parseInt(String.valueOf(players[j].charAt(1)))) {
                                System.out.print(FoxHoundUtils.HOUND_FIELD);
                            }
                        }
                    } else
                        System.out.print(".");
                }

                System.out.print(" " + x);
            }
        }

        else
        {
            for (int x = 1; x <= 9; x++) {
                System.out.print("\n0" + x + " ");
                for (char i = 'A'; i < (char) ('A' + dimension); i++) {
                    if (FoxHoundUtils.contain(players, String.valueOf(i) + x)) {
                        for (int j = 0; j < players.length; j++) {
                            if (i == players[j].charAt(0) && x == FoxHoundUtils.extractNum(players[j]) && j == (players.length - 1)) {
                                System.out.print(FoxHoundUtils.FOX_FIELD);
                            } else if (i == players[j].charAt(0) && x == FoxHoundUtils.extractNum(players[j])) {
                                System.out.print(FoxHoundUtils.HOUND_FIELD);
                            }
                        }
                    } else
                        System.out.print(".");
                }

                System.out.print(" 0" + x);

            }

            {
                for (int x = 10; x <= dimension; x++) {
                    System.out.print("\n" + x + " ");
                    for (char i = 'A'; i < (char) ('A' + dimension); i++) {
                        if (FoxHoundUtils.contain(players, String.valueOf(i) + x)) {
                            for (int j = 0; j < players.length; j++) {
                                if (i == players[j].charAt(0) && x == FoxHoundUtils.extractNum(players[j]) && j == (players.length - 1)) {
                                    System.out.print(FoxHoundUtils.FOX_FIELD);
                                } else if (i == players[j].charAt(0) && x == FoxHoundUtils.extractNum(players[j])) {
                                    System.out.print(FoxHoundUtils.HOUND_FIELD);
                                }
                            }
                        } else
                            System.out.print(".");
                    }

                    System.out.print(" " + x);
                }
            }
        }

        System.out.print("\n");

        //create the below row alphabet
        {
            if (dimension <= 9) {
                System.out.print("\n  ");
                for (char i = 'A'; i < (65 + dimension); i++) {
                    System.out.print(i);
                }
            }
            else {
                System.out.print("\n   ");
                for (char i = 'A'; i < (65 + dimension); i++) {
                    System.out.print(i);
                }
            }

            System.out.print("\n");
        }
    }

    /**
     *
     * @param players = the current positions of the pieces
     * @param dimension = the board's dimension
     */
    public static void displayBoardFancy(String[] players, int dimension) {
        //the dimension <= 9 is meant to make the ascii graphic more cleaner
        //create the above row alphabet
        {
            if (dimension <= 9) {
                System.out.print("   ");
                for (char i = 'A'; i < (65 + dimension); i++) {
                    System.out.print("  " + i + " ");
                }
            }
            //same as above code, but include more spaces because involve double digit
            else {
                System.out.print("    ");
                for (char i = 'A'; i < (65 + dimension); i++) {
                    System.out.print("  " + i + " ");
                }
            }
        }

        //creating the bar (this is meant for the first bar)
        //different bar also been created for double digits
        {
            if (dimension <= 9) {
                System.out.print("\n   |");
                for (int i = 1; i <= dimension; i++) {
                    System.out.print("===|");
                }
            }
            else {
                System.out.print("\n    |");
                for (int i = 1; i <= dimension; i++) {
                    System.out.print("===|");
                }
            }
        }

        if (dimension <= 9) {
            for (int x = 1; x <= dimension; x++) {
                System.out.print("\n" + x + "  |");
                for (char i = 'A'; i < (char) ('A' + dimension); i++) {
                    if (FoxHoundUtils.contain(players, String.valueOf(i) + x)) {
                        for (int j = 0; j < 5; j++) {
                            if (i == players[j].charAt(0) && x == Integer.parseInt(String.valueOf(players[j].charAt(1))) && j == 4) {
                                System.out.print(" " + FoxHoundUtils.FOX_FIELD + " |");
                            } else if (i == players[j].charAt(0) && x == Integer.parseInt(String.valueOf(players[j].charAt(1)))) {
                                System.out.print(" " + FoxHoundUtils.HOUND_FIELD + " |");
                            }
                        }
                    } else
                        System.out.print("   |");
                }

                System.out.print("  " + x);

                System.out.print("\n   |");
                for (int i = 1; i <= dimension; i++) {
                    System.out.print("===|");
                }
            }
        }

        else
        {
            for (int x = 1; x <= 9; x++) {
                System.out.print("\n0" + x + "  |");
                for (char i = 'A'; i < (char) ('A' + dimension); i++) {
                    if (FoxHoundUtils.contain(players, String.valueOf(i) + x)) {
                        for (int j = 0; j < players.length; j++) {
                            if (i == players[j].charAt(0) && x == FoxHoundUtils.extractNum(players[j]) && j == (players.length - 1)) {
                                System.out.print(" " + FoxHoundUtils.FOX_FIELD + " |");
                            } else if (i == players[j].charAt(0) && x == FoxHoundUtils.extractNum(players[j])) {
                                System.out.print(" " + FoxHoundUtils.HOUND_FIELD + " |");
                            }
                        }
                    } else
                        System.out.print("   |");
                }

                System.out.print("  0" + x);

                System.out.print("\n    |");
                for (int i = 1; i <= dimension; i++) {
                    System.out.print("===|");
                }
            }

            {
                for (int x = 10; x <= dimension; x++) {
                    System.out.print("\n" + x + "  |");
                    for (char i = 'A'; i < (char) ('A' + dimension); i++) {
                        if (FoxHoundUtils.contain(players, String.valueOf(i) + x)) {
                            for (int j = 0; j < players.length; j++) {
                                if (i == players[j].charAt(0) && x == FoxHoundUtils.extractNum(players[j]) && j == (players.length - 1)) {
                                    System.out.print(" " + FoxHoundUtils.FOX_FIELD + " |");
                                } else if (i == players[j].charAt(0) && x == FoxHoundUtils.extractNum(players[j])) {
                                    System.out.print(" " + FoxHoundUtils.HOUND_FIELD + " |");
                                }
                            }
                        } else
                            System.out.print("   |");
                    }

                    System.out.print("  " + x);

                    System.out.print("\n    |");
                    for (int i = 1; i <= dimension; i++) {
                        System.out.print("===|");
                    }
                }
            }
        }

        //create the below row alphabet
        {
            if (dimension <= 9) {
                System.out.print("\n   ");
                for (char i = 'A'; i < (65 + dimension); i++) {
                    System.out.print("  " + i + " ");
                }
            }
            else {
                System.out.print("\n    ");
                for (char i = 'A'; i < (65 + dimension); i++) {
                    System.out.print("  " + i + " ");
                }
            }

            System.out.print("\n");
        }
    }

    /**
     * Print the main menu and query the user for an entry selection.
     * 
     * @param figureToMove the figure type that has the next move
     * @param stdin a Scanner object to read user input from
     * @return a number representing the menu entry selected by the user
     * @throws IllegalArgumentException if the given figure type is invalid
     * @throws NullPointerException if the given Scanner is null
     */
    public static int mainMenuQuery(char figureToMove, Scanner stdin) {
        Objects.requireNonNull(stdin, "Given Scanner must not be null");
        if (figureToMove != FoxHoundUtils.FOX_FIELD 
         && figureToMove != FoxHoundUtils.HOUND_FIELD) {
            throw new IllegalArgumentException("Given figure field invalid: " + figureToMove);
        }

        String nextFigure = 
            figureToMove == FoxHoundUtils.FOX_FIELD ? "Fox" : "Hounds";

        int input = -1;
        while (input == -1) {
            System.out.println(nextFigure + " to move");
            System.out.println(MAIN_MENU);

            boolean validInput = false;
            if (stdin.hasNextInt()) {
                input = stdin.nextInt();
                validInput = input > 0 && input <= MENU_ENTRIES;
            }

            if (!validInput) {
                System.out.println("Please enter valid number.");
                input = -1; // reset input variable
            }

            stdin.nextLine(); // throw away the rest of the line
        }

        return input;
    }

    /**
     *
     * @param dimension = the board's dimension
     * @param stdin = the input scanner
     * @return array of two valid coordinates
     */
    public static String[] positionQuery(int dimension, Scanner stdin) {
        String[] input = new String[2];

        int cmd = -1;
        while (cmd == -1) {
            System.out.println("Provide origin and destination coordinates.\n" +
                               "Enter two positions between A1-H8:\n");

            input[0] = stdin.next();
            input[1] = stdin.next();
            String origin = input[0];
            String destination = input[1];

            if (origin.length() <= 3 || destination.length() <= 3) {
                if (!FoxHoundUtils.isCoordinate(destination) || !FoxHoundUtils.isCoordinate(origin) || !(FoxHoundUtils.extractNum(origin) > 0) || !(FoxHoundUtils.extractNum(destination) > 0)) {
                    System.err.println("ERROR: Please enter valid coordinate pair separated by space.");
                    cmd = -1;
                }

                else {
                    cmd = 1;
                }
            }
            else {
                System.err.println("ERROR: Please enter valid coordinate pair separated by space.");
                cmd = -1;
            }
        }

        return input;
    }


    /**
     *
     * @param test_in = the name of the file path
     * @return turns the name to a PATH
     */
    public static Path fileQuery(Scanner test_in) {
        System.out.println("Enter file path: ");
        Path path = Paths.get(test_in.next());
        String name = path.toString();

        if (!name.contains(".txt")) {
            System.out.println("Invalid path file");
            return null;
        }
        else
            return path;
    }

}







