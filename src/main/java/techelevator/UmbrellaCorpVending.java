package techelevator;

import techelevator.snacks.Snack;
import techelevator.util.CashBox;
import techelevator.util.ItemLoader;
import techelevator.util.TransactionLog;

import java.math.BigDecimal;
import java.util.Scanner;

public class UmbrellaCorpVending {

    private ItemLoader itemLoader;
    private CashBox cashBox;
    private TransactionLog transactionLog;

    final static String list = "List inventory";
    final static String purchase = "Purchase item";
    final static String exit = "Exit";
    final static String[] main_menu = {list, purchase, exit};
    final static String addCash = "Insert cash";
    final static String selectItem = "Select item";
    final static String[] selection_menu = {addCash, selectItem};
    final static String CSV = "vendingmachine.csv";

    final Scanner userInput = new Scanner(System.in);

    public UmbrellaCorpVending(ItemLoader itemLoader, CashBox cashBox, TransactionLog transactionLog) {
        this.itemLoader = itemLoader;
        this.cashBox = cashBox;
        this.transactionLog = transactionLog;
    }


    public static void main(String[] args) {
        ItemLoader loader = new ItemLoader();
        CashBox cash = new CashBox();
        TransactionLog log = new TransactionLog();
        UmbrellaCorpVending umbrellaCorpVending = new UmbrellaCorpVending(loader, cash, log);
        umbrellaCorpVending.run();
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
                    }
                } else if (select == 3) {
                    handleChangeReturn();
                    break;
//                } else {
//                    System.out.println();
                }
            } catch (Exception e) {
                System.out.println("Invalid entry occurred, try again.");
            }
        }

    }

    private void handleCash() {
        try {
            System.out.printf("Balance: %s \n Please enter whole dollar amounts >>> ", cashBox.getBalance());
            BigDecimal cashInput = new BigDecimal(userInput.nextLine());
            cashBox.insertCash(cashInput);
            transactionLog.writeToLog("Cash input", cashInput, cashBox.getBalance());
        } catch (NumberFormatException e) {
            System.out.println("This machine only accepts whole dollar amounts.");
        }
    }

    private void handleTransaction() {
        try {
            System.out.printf("Balance %s \n Enter an item code to make a purchase >>> ", cashBox.getBalance());
            String code = userInput.nextLine();
            for (Snack snack : itemLoader.getSnacks()) {
                if (code.equalsIgnoreCase(snack.getSlotCode())) {
                    if (cashBox.getBalance().compareTo(snack.getPrice()) >= 0 && itemLoader.getQuantity().get(snack.getSlotCode()) != 0) {
                        cashBox.debitBalance(snack.getPrice());
                        itemLoader.dispense(snack.getSlotCode());
                        System.out.printf("You bought %s for $%s ", snack.getName(), snack.getPrice());
                        System.out.println("\n" + snack.getSound());
                    }
                }
                transactionLog.writeToLog(snack.getName(), snack.getPrice(), cashBox.getBalance());
            }

        } catch (IllegalArgumentException e) {
            System.out.println("Item out of stock or insufficient balance.");
        }
    }

    public void handleChangeReturn() {
        transactionLog.writeToLog("Cashed out", cashBox.getBalance(), BigDecimal.ZERO);
        System.out.println(cashBox.returnChange());
        cashBox.resetBalance();
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
