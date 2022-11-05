package techelevator.util;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class CashBox {

    private final BigDecimal QUARTER = new BigDecimal("0.25");
    private final BigDecimal DIME = new BigDecimal(".10");
    private final BigDecimal NICKEL = new BigDecimal(".05");

    private BigDecimal balance = new BigDecimal("0.00");

    public BigDecimal getBalance() {
        return balance;
    }

    public BigDecimal insertCash(BigDecimal cash) {
        BigDecimal cashRounded = cash.round(new MathContext(1, RoundingMode.DOWN));
        return balance = balance.add(cashRounded);
    }

    public BigDecimal debitBalance(BigDecimal snackPrice) {
        if (balance.compareTo(snackPrice) == -1) {
            throw new ArithmeticException();
        } else {
            balance = balance.subtract(snackPrice);
        }
        return balance;
    }

    public BigDecimal resetBalance() {
        return balance = new BigDecimal("0.00");
    }

    public String returnChange() {

        int qChange = 0;
        int dChange = 0;
        int nChange = 0;

        while (getBalance().compareTo(QUARTER) >= 0) {
            qChange += 1;
            debitBalance(QUARTER);
        }
        while (getBalance().compareTo(DIME) >= 0) {
            dChange += 1;
            debitBalance(DIME);
        }
        while (getBalance().compareTo(NICKEL) >= 0) {
            nChange += 1;
            debitBalance(NICKEL);
        }
        String change = String.format("Your change is %d Quarters, %d Dimes, %d Nickels", qChange, dChange, nChange);
        return change;
    }
}


