package techelevator.util;

import java.util.Scanner;


public class MenuService {

    private final Scanner scanner = new Scanner(System.in);

    public int menuSelection(String selection) {
        int menuSelection;
        System.out.println(selection);
        try {
            menuSelection = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            menuSelection = -1;
        }
        return menuSelection;
    }

    public String promptForSelection(String prompt) {
        System.out.println(prompt);
        return scanner.nextLine();
    }

    public void printError(String errorMsg) {
        System.out.println(errorMsg);
    }

    public void printMainMenu() {
        System.out.println("*******************************");
        System.out.println("*    Umbrella Corp. Vending   *");
        System.out.println("*******************************");
        System.out.println("1. List Inventory.");
        System.out.println("2. Purchase Menu.");
        System.out.println("3. Exit. ");
    }

    public void printPurchaseMenu() {
        System.out.println("*******************************");
        System.out.println("*        Purchase Menu        *");
        System.out.println("*******************************");
        System.out.println("1. Insert Cash.");
        System.out.println("2. Purchase Item.");
        System.out.println("3. Finish Transaction. ");
    }

    public void listInventory(Object objects) {
        if (objects != null) {
            System.out.println(objects);
        }
    }

}
