package domain.models;


import java.util.Scanner;

public class Input {
    static final Scanner input = new Scanner(System.in);

    public static double nextDouble(String txt) {
        System.out.print(txt);
        double numberDouble = Double.parseDouble(input.next().trim().replace(',', '.'));
        return numberDouble;
    }

    public static int nextInt(String txt) {
        System.out.print(txt);
        int numberInt = Integer.parseInt(input.next().trim());
        return numberInt;
    }

    public static String next(String txt) {
        System.out.print(txt);
        String str = input.next().trim();
        return str;
    }

    public static void close() {
        input.close();
    }
}
