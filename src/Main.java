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

                // [+] Set up Products
                case 1: {
                    if (stock != null) {
                        System.out.println("[!] Stock is already inserted [!]");
                        break;
                    }
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

                // [#] View Product
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

                // [+] Insert Product
                case 3: {
                    if (stock == null) {
                        System.out.println("[!] Please set up stock first.\n");
                        break;
                    }

                    System.out.println("\n========== STOCK INSERTION ==========");
                    System.out.println("[+] Stocks available to insert:");

                    // Display stock with available slots and mark full rows
                    boolean allFull = true;
                    for (int i = 0; i < stock.length; i++) {
                        boolean isFull = true;
                        System.out.print("Catalogue [" + (i + 1) + "] : ");

                        for (int j = 0; j < stock[i].length; j++) {
                            if (stock[i][j].equals("Empty")) {
                                System.out.print("[ " + (j + 1) + " ] - ");
                                isFull = false;
                            } else {
                                System.out.print("[ Not Available ] - ");
                            }
                        }

                        if (isFull) {
                            System.out.print("<<< STOCK FULL >>>");
                        } else {
                            allFull = false;
                        }

                        System.out.println();
                    }

                    if (allFull) {
                        System.out.println("[!] All catalogues are full. Cannot insert new items.\n");
                        break;
                    }

                    int numberCatalogue;
                    int numberColumn;

                    // Ask for catalogue number that is NOT full
                    while (true) {
                        System.out.print("[+] Insert the number of catalogues : ");
                        numberCatalogue = sc.nextInt() - 1;

                        if (numberCatalogue < 0 || numberCatalogue >= stock.length) {
                            System.out.println("[!] Invalid catalogue number [!]");
                            continue;
                        }

                        // Check if selected catalogue is full
                        boolean rowFull = true;
                        for (int j = 0; j < stock[numberCatalogue].length; j++) {
                            if (stock[numberCatalogue][j].equals("Empty")) {
                                rowFull = false;
                                break;
                            }
                        }

                        if (rowFull) {
                            System.out.println("[!] This catalogue is FULL. Please choose another one.\n");
                        } else {
                            break; // Valid and not full
                        }
                    }

                    // Ask for column and validate it's empty
                    while (true) {
                        System.out.print("[+] Insert number of column : ");
                        numberColumn = sc.nextInt() - 1;

                        if (numberColumn >= 0 && numberColumn < stock[numberCatalogue].length) {
                            if (!stock[numberCatalogue][numberColumn].equals("Empty")) {
                                System.out.println("[!] This slot is already taken by: \"" + stock[numberCatalogue][numberColumn] + "\"");
                                System.out.println("[!] Please choose another column.\n");
                                continue;
                            }
                            break;
                        }

                        System.out.println("[!] Invalid column number [!]");
                    }

                    // Input item name
                    System.out.print("[+] Insert name of item : ");
                    sc.nextLine(); // consume newline
                    String nameItem = sc.nextLine();
                    stock[numberCatalogue][numberColumn] = nameItem;

                    System.out.println("[+] Successfully Inserted the Item [+]");

                    // Optional: Show if catalogue is now full
                    boolean isNowFull = true;
                    for (int j = 0; j < stock[numberCatalogue].length; j++) {
                        if (stock[numberCatalogue][j].equals("Empty")) {
                            isNowFull = false;
                            break;
                        }
                    }

                    if (isNowFull) {
                        System.out.println("\n[+] Catalogue " + (numberCatalogue + 1) + " is now FULL:");
                        for (int j = 0; j < stock[numberCatalogue].length; j++) {
                            System.out.println(" - Column " + (j + 1) + ": " + stock[numberCatalogue][j]);
                        }
                        System.out.println(">>> STOCK FULL <<<\n");
                    }

                    break;
                }

                // [+] Update Product
                case 4: {
                    if (stock == null) {
                        System.out.println("[!] Please set up stock first.\n");
                        break;
                    }
                    System.out.println("\n========== STOCK UPDATE ==========");
                    System.out.println("[+] Stocks available to update => ");
                    for (int i =0; i < stock.length; i++) {
                        System.out.print("Catalogue [" + (i + 1) + "] : ");
                        for (int j = 0; j < stock[i].length; j++) {
                            System.out.print("[ " + (j + 1) + " - " + stock[i][j] + " ] ");
                        }
                    }
                    int row, column;
                    while (true) {
                        System.out.println("[+] Insert the number of catalogues to update : ");
                        row = sc.nextInt() - 1;
                        if (row < 0 || row >= stock.length) {
                            System.out.println("[!] Invalid row number [!]");
                            break;
                        }
                    }
                    while (true){
                        System.out.println("[+] Insert number of columns to update : ");
                        column = sc.nextInt() - 1;
                        if (column < 0 || column >= stock[row].length) {
                            System.out.println("[!] Invalid column number [!]");
                            break;
                        }
                    }

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
        }
    }
}
