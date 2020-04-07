import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

/**
 * A utility class for the fox hound program.
 * 
 * It contains helper functions for all file input / output
 * related operations such as saving and loading a game.
 */
public class FoxHoundIO {


    /**
     *
     * @param players = the current positions of the pieces
     * @param figure = the turn
     * @param file = which file path to save
     * @return true if the file is successfully saved
     * @throws IllegalArgumentException = if the dimension in the load file is not >= 4 and <= 26
     */
    public static boolean saveGame(String[] players, char figure, Path file) {
        boolean b = false;
        String fig =  String.valueOf(figure);
        String list = "";

        int n = players.length;
        int dimension = 8;

        for (int i = 0; i < n; i++) {
            if (FoxHoundUtils.extractNum(players[i]) > dimension)
                throw new IllegalArgumentException("Coordinate is out of Bounds");
        }

        for (int i = 0; i < players.length; i++) {
            list = list + " " + players[i];
        }

        if (Files.exists(file)) {
            System.out.println("File already exist. Please try again");
        }
        else {
            try {
                Files.createFile(file);
                Files.writeString(file, fig, StandardOpenOption.APPEND);
                Files.writeString(file, list, StandardOpenOption.APPEND);
                System.out.println("Save data has been created");
                b = true;
            } catch (NoSuchFileException e) {
                System.out.println("Path does not exist");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return b;
    }

    /**
     *
     * @param players = the current positions of the pieces
     * @param file = which file path to load
     * @return a player's turn based on the load game
     * @throws IllegalArgumentException = if the dimension in the load file is not >= 4 and <= 26
     */
    public static char loadGame(String[] players, Path file) {
        char figure;
        if (Files.exists(file)) {
            int n = players.length;
            int dimension = 8;

            for (int i = 0; i < n; i++) {
                if (FoxHoundUtils.extractNum(players[i]) > dimension)
                    throw new IllegalArgumentException("Invalid save file");
            }

            //to make an array of available letters
            char[] alphabet = new char[dimension];
            for (char i = 'A'; i < (dimension + 65); i++) {
                alphabet[i - 65] = i;
            }

            //to make an array of available numbers
            int[] numbers = new int[dimension];
            for (int i = 0; i < dimension; i++) {
                numbers[i] = i + 1;
            }

            try {
                String content = Files.readString(file);
                figure = content.charAt(0);

                if ((figure == FoxHoundUtils.HOUND_FIELD || figure == FoxHoundUtils.FOX_FIELD)) {
                    String[] loadFile = content.split(" ");
                    if (n == (loadFile.length - 1)) {
                        for (int i = 0; i < n; i++) {
                            if (FoxHoundUtils.contain(alphabet, loadFile[i + 1].charAt(0)) && FoxHoundUtils.contain(numbers, (FoxHoundUtils.extractNum(loadFile[i + 1])))) {
                                players[i] = loadFile[i + 1];
                            } else {
                                System.err.println("Invalid save file");
                                figure = '#';
                                return figure;
                            }
                        }

                        return figure;
                    } else {
                        System.err.println("Invalid save file");
                    }
                } else {
                    System.err.println("Invalid save file");
                }
            } catch (IOException e) {
                System.err.println("IOException");
            }
            figure = '#';
        }

        else {
            System.err.println("FILE DOES NOT EXISTS");
            figure = '#';
        }
        return figure;
    }
}
