import java.util.Arrays;

/**
 * A utility class for the fox hound program.
 * 
 * It contains helper functions to check the state of the game
 * board and validate board coordinates and figure positions.
 */
public class FoxHoundUtils {

    // ATTENTION: Changing the following given constants can 
    // negatively affect the outcome of the auto grading!

    /**
     * Default dimension of the game board in case none is specified.
     */
    public static final int DEFAULT_DIM = 8;
    /**
     * Minimum possible dimension of the game board.
     */
    public static final int MIN_DIM = 4;
    /**
     * Maximum possible dimension of the game board.
     */
    public static final int MAX_DIM = 26;

    /**
     * Symbol to represent a hound figure.
     */
    public static final char HOUND_FIELD = 'H';
    /**
     * Symbol to represent the fox figure.
     */
    public static final char FOX_FIELD = 'F';

    // HINT Write your own constants here to improve code readability ...

    public static String[] initialisePositions(int dimension) {

        if (dimension >= 4 && dimension <= 26) {

            //To create the number of pieces in a given dimension
            int boardPieces = (dimension / 2) + 1;

            //To create the array of positions
            String[] positions = new String[boardPieces];

            //positions for the hounds
            int value = 0;
            String letter = "";

            for (int i = 1; i < boardPieces; i++) {
                value = 'B' + (i - 1) * 2;
                letter = Character.toString(value);
                positions[i - 1] = letter + 1;
            }

            //position for the fox
            //if dimension is an even number
            if (dimension % 2 == 0) {
                value = 'A' + (dimension / 2);
                letter = Character.toString(value);
                positions[boardPieces - 1] = letter + dimension;
            }
            //if dimension is an odd number
            else {
                value = 'A' + ((dimension - 1) / 2) - 1;
                letter = Character.toString(value);
                positions[boardPieces - 1] = letter + dimension;
            }

            return positions;
        }

        //if input < 4 or input > 26, give an error message
        else {
            throw new IllegalArgumentException("Dimension should be from 4 until 26");
        }
    }

    /**
     * @param players = take the array of the pieces' positions
     * @param origin  = the original position
     * @return true if the origin contained in the players
     */
    public static boolean contain(String[] players, String origin) {
        boolean b = false;
        for (String i : players) {
            if (i.equals(origin)) {
                b = true;
            }
        }
        return b;
    }

    public static boolean contain(String[] players, String[] positions) {
        int count = 0;
        int n = players.length;
        int p = positions.length;

        if (n < p)
            System.err.println("The subset is larger than the set");

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < p; j++) {
                if (players[i].equals(positions[j])) {
                    count = count + 1;
                }
            }
        }

        if (count == p)
            return true;
        else
            return false;
    }

    public static boolean contain(char[] letters, char letter) {
        for (char i : letters) {
            if (i == letter)
                return true;
        }

        return false;
    }

    public static boolean contain(int[] numbers, int number) {
        for (int i : numbers) {
            if (i == number)
                return true;
        }

        return false;
    }

    /**
     * @param coordinate
     * @return extract the number in the coordinate
     */
    public static int extractNum(String coordinate) {
        int num;
        num = Integer.parseInt(coordinate.substring(1));
        return num;
    }

    /**
     * @param players     = the array of coordinates of the pieces
     * @param figure      = the turn
     * @param givenOrigin = the first input of the player
     * @return true if the given origin is NOT in the array of players
     */
    public static boolean isOriginNotValid(String[] players, char figure, String givenOrigin) {

        String foxPos = players[players.length - 1];
        int numberOfHounds = players.length - 1;

        if (figure == FOX_FIELD) {
            if (!givenOrigin.equals(foxPos))
                return true;
        }

        if (figure == HOUND_FIELD) {
            int counter = 0;
            //to see if the given origin contains a hound in the given player arrays
            //counter is created because if there at least one equal, that means there is a piece by the given origin
            for (int i = 0; i < numberOfHounds; i++) {
                if (players[i].equals(givenOrigin)) {
                    counter = 1;
                }
            }

            if (!(counter == 1))
                return true;
        }

        return false;
    }

    /**
     * @param players     = the array of coordinates of the pieces
     * @param destination = the second input of the player
     * @return true if destination of the given coordinate is occupied by another piece
     */
    public static boolean isDestinationOccupied(String[] players, String destination) {
        if (contain(players, destination))
            return true;
        else
            return false;
    }

    /**
     * @param dimension   = dimension of the board
     * @param figure      = the turn
     * @param origin      = the origin coordinate
     * @param destination = the destination coordinate
     * @return true if the move is possible for based on its range
     */
    public static boolean isInRange(int dimension, char figure, String origin, String destination) {

        char letter = origin.charAt(0);
        char nextLetter = destination.charAt(0);
        int num = extractNum(origin);
        int nextNum = extractNum(destination);

        if (nextNum > dimension || nextNum <= 0)
            return false;

        if (figure == FOX_FIELD) {
            if ((letter == nextLetter - 1 || letter == nextLetter + 1) && (num == nextNum + 1 || num == nextNum - 1))
                return true;
        } else if (figure == HOUND_FIELD) {
            if ((letter == nextLetter - 1 || letter == nextLetter + 1) && num == nextNum - 1)
                return true;
        }

        return false;
    }

    /**
     * @param coordinate = input coordinate
     * @return true if it is a coordinate
     */
    public static boolean isCoordinate(String coordinate) {

        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int n = coordinate.length();

        if (alphabet.contains(Character.toString(coordinate.charAt(0)))) {
            if (n <= 3)
                return true;
        }

        return false;
    }

    public static boolean isValidMove(int dimension, String[] players, char figure, String origin, String destination) {
        //to check if origin contained in players
        if (origin == null || destination == null)
            throw new NullPointerException("ERROR: NULL INPUT");

        if (!contain(players, origin)) {
            System.out.println("NO PIECE IS PRESENT AT THE GIVEN ORIGIN\nPLEASE TRY AGAIN");
            return false;
        }

        if (isOriginNotValid(players, figure, origin)) {
            System.out.println("NO PIECE IS PRESENT AT THE GIVEN ORIGIN\nPLEASE TRY AGAIN");
            return false;
        }

        if (isDestinationOccupied(players, destination)) {
            System.out.println("THE DESTINATION IS ALREADY OCCUPIED BY A PIECE\nPLEASE TRY AGAIN");
            return false;
        }

        if (!isInRange(dimension, figure, origin, destination)) {
            System.out.println("THE RANGE IS IMPOSSIBLE FOR THE PIECE\nPLEASE TRY AGAIN");
            return false;
        }

        else
            return true;

    }

    public static boolean isFoxWin(String coordinate) {

        //throw an error if coordinate is null
        if (coordinate == null)
            throw new NullPointerException("Null Coordinate");

        if (extractNum(coordinate) > 26)
            throw new IllegalArgumentException("The coordinate is out of given dimension");

        //create an array of possible winning positions for the fox
        String[] winPos = new String[13];
        for (int i = 0; i < 13; i++) {
            winPos[i] = Character.toString('B' + i * 2) + "1";
        }

        if (contain(winPos, coordinate))
            return true;

        else
            return false;
    }

    public static boolean isHoundWin(String[] players, int dimension) {

        if (players == null)
            throw new NullPointerException("Null input");

        if (dimension < 4 || dimension > 26)
            throw new IllegalArgumentException("Dimension out of Bounds");

        //retrieve the present coordinate of the fox
        String foxPos = players[players.length - 1];

        //creating the possible winning conditions for the hound based on the fox's positions
        String[][][] numberOfPos = new String[dimension - 2][dimension][5];
        String[][] numberOfPosTop = new String[dimension][3];
        String[][] numberOfPosBottom = new String[dimension][3];
        String[] numberOfPosCorner = new String[2];
        String[] numberOfPosSide = new String[3];

        boolean b = false;

        //when the fox is at the top
        for (int i = 0; i < dimension; i++) {
            numberOfPosTop[i][0] = Character.toString('A' + i + 1) + 2;
            numberOfPosTop[i][1] = Character.toString('A' + i - 1) + 2;
            numberOfPosTop[i][2] = Character.toString('A' + i) + 1;
            if (contain(players, numberOfPosTop[i]) && foxPos.equals(numberOfPosTop[i][2])) {
                b = true;
            }
        }

        //when the fox is in the middle area
        for (int i = 0; i < (dimension - 2); i++) {
            for (int x = 0; x < dimension; x++) {
                numberOfPos[i][x][0] = Character.toString('A' + x - 1) + (i + 1);
                numberOfPos[i][x][1] = Character.toString('A' + x + 1) + (i + 1);
                numberOfPos[i][x][2] = Character.toString('A' + x - 1) + (i + 3);
                numberOfPos[i][x][3] = Character.toString('A' + x + 1) + (i + 3);
                numberOfPos[i][x][4] = Character.toString('A' + x) + (i + 2);
                if (Arrays.equals(numberOfPos[i][x], players) && foxPos.equals(numberOfPos[i][x][4])) {
                    b = true;
                }
            }
        }

        //when the fox is at the bottom row
        for (int i = 0; i < dimension; i++) {
            numberOfPosBottom[i][0] = "" + Character.toString('A' + i + 1) + (dimension - 1);
            numberOfPosBottom[i][1] = "" + Character.toString('A' + i - 1) + (dimension - 1);
            numberOfPosBottom[i][2] = "" + Character.toString('A' + i) + (dimension);
            if (contain(players, numberOfPosBottom[i]) && foxPos.equals(numberOfPosBottom[i][2])) {
                b = true;
            }
        }

        //when the fox is on the side (left)
        for (int i = 1; i <= dimension; i++) {
            numberOfPosSide[0] = Character.toString('A' + 1) + (i - 1);
            numberOfPosSide[1] = Character.toString('A' + 1) + (i + 1);
            numberOfPosSide[2] = "A" + i;
            if (contain(players, numberOfPosSide) && foxPos.equals(numberOfPosSide[2])) {
                b = true;
            }
        }

        //when the fox is on the side (right)
        for (int i = 1; i <= dimension; i++) {
            numberOfPosSide[0] = Character.toString('A' + dimension - 2) + (i - 1);
            numberOfPosSide[1] = Character.toString('A' + dimension - 2) + (i + 1);
            numberOfPosSide[2] = Character.toString('A' + dimension - 1) + i;
            if (contain(players, numberOfPosSide) && foxPos.equals(numberOfPosSide[2])) {
                b = true;
            }
        }

        //when the fox is on the corner
        //when dimension is even
        if (dimension % 2 == 0) {
            numberOfPosCorner[0] = Character.toString('A' + 1) + (dimension - 1);
            numberOfPosCorner[1] = "A" + dimension;
        }
        //when dimension is odd
        if (dimension % 2 != 0) {
            numberOfPosCorner[0] = Character.toString('A' + dimension - 1) + (dimension - 1);
            numberOfPosCorner[1] = Character.toString('A' + dimension) + dimension;
        }
        if (contain(players, numberOfPosCorner) && foxPos.equals(numberOfPosCorner[1])) {
            b = true;
        }

        return b;
    }
}

