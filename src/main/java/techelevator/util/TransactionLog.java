package techelevator.util;

import techelevator.snacks.Snack;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TransactionLog {


    LocalDateTime date = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    String dateInLog = date.format(formatter);

    public void writeToLog(String navigationAudit, BigDecimal prePurchaseBalance, BigDecimal postPurchaseBalance) {
        try {
            File transactionLog = new File("log.txt");
            FileOutputStream fos = new FileOutputStream("log.txt", true);
            PrintWriter writer = new PrintWriter(fos);
            writer.printf("%s | %s | PreBalance: %%s | PostBalance: $%s %n", dateInLog, navigationAudit, prePurchaseBalance, postPurchaseBalance);
            writer.close();
        } catch (FileNotFoundException fnf) {
            fnf.getMessage();
        }
    }
}