package techelevator.snacks;

import java.math.BigDecimal;

public class Chip extends Snack{

    public Chip(String slotCode, String name, BigDecimal price, String type) {
        super(slotCode, name, price, type);
    }

    @Override
    public String getSound() {
        return "Crunch crunch, yum!";
    }

}
