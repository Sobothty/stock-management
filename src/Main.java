import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String[][] stock = null;
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("- ".repeat(10) + " Stock Management " + "- ".repeat(10));
            System.out.println("1. Set Up Stock");
            System.out.println("2. View Stock");
            System.out.println("3. Insert Stock");
            System.out.println("4. Update Stock");
            System.out.println("5. Delete Stock");
            System.out.println("6. View Insertion Stock");
            System.out.println("7. Exit Program");

            System.out.print("[+] Insert the Option: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1: {
                    System.out.print("[+] Insert number of Stock: ");
                    int qtyStock = sc.nextInt();
                    stock = new String[qtyStock][];

                    for (int i = 0; i < qtyStock; i++) {
                        System.out.print("[+] Insert number of columns for Stock [" + (i + 1) + "]: ");
                        int numberCatalogue = sc.nextInt();
                        stock[i] = new String[numberCatalogue];

                        for (int j = 0; j < numberCatalogue; j++) {
                            stock[i][j] = "Empty";
                        }
                    }
                    System.out.println("[+] Successfully Set Up Stock [+]\n");

                    break;
                }

                case 2: {
                    if (stock == null) {
                        System.out.println("[!] Please set up stock first.\n");
                        break;
                    }
                    System.out.println("\n========== STOCK VIEW ==========");
                    for (int i = 0; i < stock.length; i++) {
                        System.out.print("Stock [" + (i + 1) + "] => ");
                        for (int j = 0; j < stock[i].length; j++) {
                            System.out.print("[ " + (j + 1) + " - " + stock[i][j] + " ] ");
                        }
                        System.out.println();
                    }
                    System.out.println("================================\n");
                    break;
                }

                case 3: {
                    if (stock == null) {
                        System.out.println("[!] Please set up stock first.\n");
                        break;
                    }
                    System.out.println("\n========== STOCK INSERTION ==========");

                    for (int i = 0; i < stock.length; i++) {
                        System.out.print("Stock [" + (i + 1) + "] => ");
                        boolean isRowEmpty = true;

                        for (int j = 0; j < stock[i].length; j++) {
                            if (!stock[i][j].equals("Empty")) {
                                isRowEmpty = false;
                            }
                        }

                        if (isRowEmpty) {
                            // If row is entirely empty, allow user to input for all columns
                            System.out.println("This row is completely empty. You can insert values for all columns.");
                            for (int j = 0; j < stock[i].length; j++) {
                                System.out.print("[+] Enter item for Column [" + (j + 1) + "]: ");
                                stock[i][j] = sc.next(); // Insert value for each column
                            }
                        } else {
                            // If row has empty slots, prompt for specific empty slots
                            boolean hasEmpty = false; // flag to track if there's an empty slot
                            for (int j = 0; j < stock[i].length; j++) {
                                if (stock[i][j].equals("Empty")) {
                                    System.out.print("[+] Enter item for Column [" + (j + 1) + "]: ");
                                    stock[i][j] = sc.next();
                                    hasEmpty = true;
                                }
                            }
                            if (!hasEmpty) {
                                System.out.println("This stock is full.");
                            }
                        }
                    }
                    System.out.println("[+] Stock Updated [+]\n");
                    break;
                }

                case 7: {
                    System.out.println("Exiting program. Goodbye!");
                    sc.close();
                    System.exit(0);
                    return;
                }

                default: {
                    System.out.println("[!] Invalid option, please try again.\n");
                    break;
                }
            }

            // After each operation, check if the stock is full and display the updated stock
            if (stock != null) {
                System.out.println("\n========== UPDATED STOCK ==========");
                for (int i = 0; i < stock.length; i++) {
                    System.out.print("Stock [" + (i + 1) + "] => ");
                    boolean isFull = true;
                    for (int j = 0; j < stock[i].length; j++) {
                        System.out.print("[ " + (j + 1) + " - " + stock[i][j] + " ] ");
                        if (stock[i][j].equals("Empty")) {
                            isFull = false;
                        }
                    }
                    if (isFull) {
                        System.out.println("This stock is full.");
                    } else {
                        System.out.println(); // move to next line for empty slots
                    }
                }
                System.out.println("================================\n");
            }
        }
    }
}
