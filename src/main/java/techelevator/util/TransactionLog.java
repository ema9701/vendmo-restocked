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
import java.util.Map;

public class TransactionLog {

    private LocalDateTime date = LocalDateTime.now();
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    private String dateInLog = date.format(formatter);
    private File transactionLog;

    public TransactionLog() {
        transactionLog = new File("log.txt");
    }


    public void writeToLog(String navigationAudit, BigDecimal prePurchaseBalance, BigDecimal postPurchaseBalance) {
        try {
            FileOutputStream fos = new FileOutputStream(transactionLog, true);
            PrintWriter writer = new PrintWriter(transactionLog);
            writer.printf("%s | %s | PreBalance: $%s | PostBalance: $%s %n", dateInLog, navigationAudit, prePurchaseBalance, postPurchaseBalance);
            writer.close();
        } catch (FileNotFoundException fnf) {
            fnf.getMessage();
        }
    }


}
