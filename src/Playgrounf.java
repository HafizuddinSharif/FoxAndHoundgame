import java.util.Arrays;
import java.util.Scanner;

public class Playgrounf {

    public static int counter(int count) {
        for (int i = 0; i < 10; i++) {
            count = count + i;
        }

        return count;
    }

    public static void main (String[] args) {
        int count = counter(0);

        System.out.print(count);
    }
}
