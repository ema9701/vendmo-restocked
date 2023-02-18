package techelevator;

import techelevator.snacks.Snack;
import techelevator.util.CashBox;
import techelevator.util.ItemLoader;
import techelevator.util.MenuService;
import techelevator.util.TransactionLog;

import java.math.BigDecimal;

public class UmbrellaCorpVending {

    final static String CSV = "vendingmachine.csv";
    private final ItemLoader itemLoader;
    private final CashBox cashBox;
    private final TransactionLog transactionLog;
    private final MenuService menu;

    public UmbrellaCorpVending() {
        this.itemLoader = new ItemLoader();
        this.cashBox = new CashBox();
        this.transactionLog = new TransactionLog();
        this.menu = new MenuService();
    }

    public static void main(String[] args) {
        UmbrellaCorpVending vending = new UmbrellaCorpVending();
        vending.run();
    }

    private void run() throws NullPointerException {
        itemLoader.loadInventory(CSV);
        int menuSelection = -1;
        while (menuSelection != 3) {
            menu.printMainMenu();
            menuSelection = menu.menuSelection("Select an option >>> ");
            if (menuSelection == 1) {
                menu.listInventory(itemLoader.listCurrentInventory());
            } else if (menuSelection == 2) {
                handlePurchaseMenu();
            } else if (menuSelection == 3) {
                return;
            } else {
                menu.printError("Invalid selection");
            }
        }
    }

    private void handlePurchaseMenu() {
        int menuSelection = -1;
        while (menuSelection != 3) {
            menu.printPurchaseMenu(cashBox.getBalance());
            menuSelection = menu.menuSelection("Select an option >>> ");
            if (menuSelection == 1) {
                handleCash();
            } else if (menuSelection == 2) {
                handleTransaction();
            } else if (menuSelection == 3) {
                finishTransaction();
                return;
            } else {
                menu.printError("**Invalid selection**");
            }
        }
    }

    private void handleCash() {
         {
            try {
                System.out.printf("Balance: $%s \n", cashBox.getBalance());
                BigDecimal cashInput = new BigDecimal(menu.promptForSelection("Please enter whole dollar amounts >>> "));
                cashBox.addCash(cashInput);
                transactionLog.writeToLog("Cash input", cashInput, cashBox.getBalance());
                System.out.printf("Balance: $%s \n", cashBox.getBalance());
            } catch (NumberFormatException e) {
                menu.printError("**This machine only accepts whole dollar amounts**");
            }
        }
    }

    private void handleTransaction() {
        try {
            menu.listInventory(itemLoader.listCurrentInventory());
            System.out.printf("Balance: %s \n", cashBox.getBalance());
            String code = menu.promptForSelection("Enter an item code >>> ");
            Snack snack = itemLoader.getNameFromCode(code);
            cashBox.debitBalance(snack.getPrice());
            itemLoader.dispense(snack.getSlotCode());
            System.out.printf("You bought %s for $%s ", snack.getName(), snack.getPrice());
            System.out.println("\n" + snack.getSound());
            transactionLog.writeToLog(snack.getName(), snack.getPrice(), cashBox.getBalance());
        } catch (ArithmeticException | IllegalArgumentException e) {
            menu.printError("**Item out of stock or insufficient balance**");
        }
    }

    public void finishTransaction() {
        transactionLog.writeToLog("Cashed out", cashBox.getBalance(), BigDecimal.ZERO);
        System.out.println(cashBox.returnChange());
    }
}
