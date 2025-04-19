import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Main {
    public static void main(String[] args) {
        String[][] stock = null;
        ArrayList<String> insertionHistory = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm dd-MM-yyyy");


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

                    LocalDateTime now = LocalDateTime.now();
                    String timestamp = dtf.format(now);
                    String log = "Inserted item \"" + nameItem + "\" into Catalogue [" + (numberCatalogue + 1) + "], Column [" + (numberColumn + 1) + " ], " + " [ " + timestamp +" ] ";
                    insertionHistory.add(log);


                    System.out.println("[+] Successfully Inserted the Item [+]");

                    // Show catalogue is now full
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
                    for (int i = 0; i < stock.length; i++) {
                        System.out.println("Catalogue [" + (i + 1) + "] : ");
                        for (int j = 0; j < stock[i].length; j++) {
                            System.out.print((!stock[i][j].equals("Empty") ? "[ " + (j + 1) + " - " + stock[i][j] + " ] " : ""));
                        }
                        System.out.println();
                    }
                    int row, column;
                    while (true) {
                        System.out.print("[+] Insert the number of catalogues to update : ");
                        row = sc.nextInt() - 1;
                        if (row >= 0 || row < stock.length)
                            break;
                        System.out.println("[!] Invalid cataslogue number [!]");
                    }
                    while (true) {
                        System.out.print("[+] Insert number of columns to update : ");
                        column = sc.nextInt() - 1;
                        if (column >= 0 && column < stock[row].length) break;
                        System.out.println("[!] Invalid column number.");
                    }
                    if (stock[row][column].equals("Empty")) {
                        System.out.println("[!] No item found in that slot [!]");
                    }

                    System.out.println("[$] Current item : " + stock[row][column]);
                    System.out.print("[+] Insert current name of item : ");
                    sc.nextLine();
                    String currentName = sc.nextLine();
                    if (stock[row][column].equals(currentName)) {
                        System.out.print("[+] Insert new name of item : ");
                        String newName = sc.nextLine();
                        stock[row][column] = newName;
                        System.out.println("[+] Successfully update new name of item [+]");
                        break;
                    } else {
                        System.out.println("[!] Invalid name [!]");
                    }
                    break;
                }

                case 5:{
                    if (stock == null) {
                        System.out.println("[!] Please set up stock first.\n");
                        break;
                    }
                    System.out.println("\n========== STOCK DELETE ==========");
                    for (int i = 0; i < stock.length; i++) {
                        System.out.print("Catalogue [" + (i + 1) + "] : ");
                        for (int j = 0; j < stock[i].length; j++) {
                            System.out.println((!stock[i][j].equals("Empty") ? "[ " + (j + 1) + " - " + stock[i][j] + " ] " : ""));
                        }
                        System.out.println();
                    }
                    int row, column;
                    while (true) {
                        System.out.print("[+] Insert the number of catalogues to delete : ");
                        row = sc.nextInt() - 1;
                        if (row >= 0 || row < stock.length)break;
                        System.out.println("[!] Invalid catalogue number [!]");
                    }

                    while (true) {
                        System.out.print("[+] Insert number of columns to delete : ");
                        column = sc.nextInt() - 1;
                        if (column >= 0 && column < stock[row].length) break;
                        System.out.println("[!] Invalid column number.");
                    }
                    if (stock[row][column].equals("Empty")) {
                        System.out.println("[!] No item found in that slot [!]");
                        break;
                    }
                    System.out.println("[$] Current item : " + stock[row][column]);
                    System.out.print("[$] Delete current name of item : ");
                    sc.nextLine();
                    String currentName = sc.nextLine();
                    if (stock[row][column].equals(currentName)) {
                        stock[row][column] = "Empty";
                        System.out.println("[+] Successfully delete of item [+]");
                        break;
                    }
                }

                case 6:{
                    if (stock == null) {
                        System.out.println("[!] Please set up stock first.\n");
                    }
                    if (insertionHistory.isEmpty()){
                        System.out.println("[!] No item found in that slot [!]");
                    }else {
                        System.out.println("\n========== INSERTION HISTORY ==========");
                        for (int i = 0; i < insertionHistory.size(); i++) {
                            System.out.println((i + 1) + "." + insertionHistory.get(i));
                        }
                    }
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
        }
    }
}
