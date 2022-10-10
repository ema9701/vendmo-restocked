package techelevator.snacks;

import java.math.BigDecimal;

public class Soda extends Snack{

    public Soda(String slotCode, String name, BigDecimal price, String type) {
        super(slotCode, name, price, type);
    }

    @Override
    public String getSound() {
        return "Glug glug, yum!";
    }
}
