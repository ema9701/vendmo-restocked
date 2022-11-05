package techelevator;

import techelevator.snacks.Snack;
import techelevator.util.CashBox;
import techelevator.util.ItemLoader;
import techelevator.util.TransactionLog;

import java.io.*;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class UmbrellaCorpVending {

    final static String list = "List inventory";
    final static String purchase = "Purchase item";
    final static String exit = "Exit";
    final static String[] main_menu = {list, purchase, exit};
    final static String addCash = "Insert cash";
    final static String selectItem = "Select item";
    final static String finishTransaction = "Finish transaction";
    final static String[] selection_menu = {addCash, selectItem, finishTransaction};
    final static String CSV = "vendingmachine.csv";

    private final ItemLoader itemLoader;
    private final CashBox cashBox;
    private final TransactionLog transactionLog;
    final Scanner userInput = new Scanner(System.in);

    public UmbrellaCorpVending() {
        this.itemLoader = new ItemLoader();
        this.cashBox = new CashBox();
        this.transactionLog = new TransactionLog();
    }


    public static void main(String[] args) {
        UmbrellaCorpVending vending = new UmbrellaCorpVending();
        vending.run();
    }


    private void run() throws NumberFormatException, NullPointerException {
        itemLoader.loadInventory(CSV);
        while (true) {
            MainMenuDisplay();
            try {
                String mainSelect = userInput.nextLine();
                Integer select = Integer.parseInt(mainSelect);
                if (select == 1) {
                    System.out.println(itemLoader.listCurrentInventory());
                } else if (select == 2) {
                    selectionDisplay();
                    Integer purchaseSelect = Integer.parseInt(userInput.nextLine());
                    if (purchaseSelect == 1) {
                        handleCash();
                    } else if (purchaseSelect == 2) {
                        System.out.println(itemLoader.listCurrentInventory());
                        handleTransaction();
                    } else if (purchaseSelect == 3) {
                        finishTransaction();
                    }
                } else if (select == 3) {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Invalid entry occurred, try again.");
            }
        }

    }

    private void handleCash() {
        while (true) {
            try {
                System.out.printf("Balance: $%s Please enter whole dollar amounts >>> ", cashBox.getBalance());
                BigDecimal cashInput = new BigDecimal(userInput.nextLine());
                cashBox.insertCash(cashInput);
                transactionLog.writeToLog("Cash input", cashInput, cashBox.getBalance());
                System.out.printf("Balance: $%s Add more cash? Y/N", cashBox.getBalance());
                if (userInput.nextLine().equalsIgnoreCase("y")) {
                    continue;
                } else {
                    return;
                }
            } catch (NumberFormatException e) {
                System.out.println("**This machine only accepts whole dollar amounts**");
            }
        }
    }

    private void handleTransaction() {
        try {
            System.out.printf("Balance: %s \n" + "Enter an item code to make a purchase >>> ", cashBox.getBalance());
            String code = userInput.nextLine();
            Snack snack = itemLoader.getNameFromCode(code);
            cashBox.debitBalance(snack.getPrice());
            itemLoader.dispense(snack.getSlotCode());
            System.out.printf("You bought %s for $%s ", snack.getName(), snack.getPrice());
            System.out.println("\n" + snack.getSound());
            transactionLog.writeToLog(snack.getName(), snack.getPrice(), cashBox.getBalance());
        } catch (IllegalArgumentException e) {
            System.out.println("**Item out of stock or insufficient balance**");
        }
    }

    public void finishTransaction() {
        transactionLog.writeToLog("Cashed out", cashBox.getBalance(), BigDecimal.ZERO);
        System.out.println(cashBox.returnChange());
    }

    private void MainMenuDisplay() {
        for (int i = 0; i < main_menu.length; i++) {
            int optionNum = i + 1;
            System.out.println(optionNum + ") " + main_menu[i]);
        }
        System.out.println(System.lineSeparator() + "Select an option >>> ");
    }

    private void selectionDisplay() {
        for (int i = 0; i < selection_menu.length; i++) {
            int optionNum = i + 1;
            System.out.println(optionNum + ") " + selection_menu[i]);
        }
        System.out.println(System.lineSeparator() + "Select an option >>> ");
    }
}
