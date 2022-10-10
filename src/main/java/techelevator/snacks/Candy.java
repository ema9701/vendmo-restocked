package techelevator.snacks;

import java.math.BigDecimal;

public class Candy extends Snack {

    public Candy(String slotCode, String name, BigDecimal price, String type) {
        super(slotCode, name, price, type);
    }

    @Override
    public String getSound() {
        return "Munch munch, yum!";
    }
}
