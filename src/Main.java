import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String[][] stocks;
        System.out.println("- ".repeat(10) + " Stock Management " + "- ".repeat(10));
        System.out.println("1. Set Up Stock");
        System.out.println("2. View Stock");
        System.out.println("3. Insert Stock");
        System.out.println("4. Update Stock");
        System.out.println("5. Delete Stock");
        System.out.println("6. View Insertion Stock");
        System.out.println("7. Exit Program");

        System.out.print("[+] Insert the Option: ");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        switch (choice) {
            case 1: {
                System.out.print("[+] Insert number of Stock: ");
                Scanner stockInput = new Scanner(System.in);
                int qtyStock = stockInput.nextInt();
                String[][] stock = new String[qtyStock][];


                for (int i = 0; i < qtyStock; i++) {
                    System.out.print("[+] Insert Stock [" + (i + 1) + "] : ");
                    Scanner stockIn = new Scanner(System.in);
                    int numberCatalogue = stockIn.nextInt();
                    stock[i] = new String[numberCatalogue];

                    // Array get empty data
                    for (int j = 0; j < numberCatalogue; j++) {
                        stock[i][j] = "Empty";
                    }
                }
                System.out.println("[+] Successfully Set Up Stock [+]\n");

                // loop array
                for (String[] strings : stock) {
                    for (String string : strings) {
                        System.out.println("Stock " + "[ " + string + Arrays.toString(strings) + " ]" );
                    }
                }
            }
        }
    }
}