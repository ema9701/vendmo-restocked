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

    public void addCash(BigDecimal cash) {
        if (cash.compareTo(BigDecimal.ZERO) == 1) {
            this.balance = balance.add(cash.setScale(0, RoundingMode.FLOOR));
        } else {
            throw new NumberFormatException();
        }
    }

    public void debitBalance(BigDecimal snackPrice) {
        if (balance.compareTo(snackPrice) == -1) {
            throw new ArithmeticException();
        } else {
            this.balance = balance.subtract(snackPrice);
        }
    }

    public String returnChange() {

        BigDecimal[] qChange = getBalance().divideAndRemainder(QUARTER);
        this.balance = qChange[1];
        BigDecimal[] dChange = getBalance().divideAndRemainder(DIME);
        this.balance = dChange[1];
        BigDecimal[] nChange = getBalance().divideAndRemainder(NICKEL);
        this.balance = nChange[1];
        return String.format("Your change is %s Quarters, %s Dimes, %s Nickels", qChange[0], dChange[0], nChange[0]);
    }
}


