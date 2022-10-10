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
        BigDecimal cashRounded = cash.round(new MathContext(1, RoundingMode.HALF_DOWN));
        return balance = balance.add(cashRounded);
    }

    public BigDecimal debitBalance(BigDecimal snackPrice) {
        return balance = balance.subtract(snackPrice);
    }

    public BigDecimal resetBalance() {
        return balance = new BigDecimal("0.00");
    }

    public String returnChange() {
            BigDecimal remainingBalance = getBalance();
            int qChange = 0;
            int dChange = 0;
            int nChange = 0;
            while (remainingBalance.compareTo(QUARTER) == 1) {
                qChange += 1;
                remainingBalance = remainingBalance.subtract(QUARTER);
            }
            while (remainingBalance.compareTo(DIME)  == 1) {
                dChange += 1;
                remainingBalance = remainingBalance.subtract(DIME);
            }
            while (remainingBalance.compareTo(NICKEL) == 1) {
                nChange += 1;
                remainingBalance = remainingBalance.subtract(NICKEL);
            }
            String change =  String.format("Your change is %d Quarters, %d Dimes, %d Nickels", qChange, dChange, nChange);
            return change;
        }
    }


