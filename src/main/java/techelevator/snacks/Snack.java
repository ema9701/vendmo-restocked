package techelevator.snacks;

import java.math.BigDecimal;

public abstract class Snack {

    private String slotCode;
    private String name;
    private BigDecimal price;
    private String type;


    public Snack(String slotCode, String name, BigDecimal price, String type) {
        this.slotCode = slotCode;
        this.name = name;
        this.price = price;
        this.type = type;
    }

    public String getSlotCode() {
        return slotCode;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getType() {
        return type;
    }

    public abstract String getSound();

    @Override
    public String toString() {
        return getSlotCode() + "|" + getName() + "|" + getPrice() + "|" + getType() + "| ";
    }
}
