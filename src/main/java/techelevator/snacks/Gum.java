package techelevator.snacks;

import java.math.BigDecimal;

public class Gum extends Snack{

    public Gum(String slotCode, String name, BigDecimal price, String type) {
        super(slotCode, name, price, type);
    }

    @Override
    public String getSound() {
        return "Chew chew, yum!";
    }

}
