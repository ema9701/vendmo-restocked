package techelevator.util;

import techelevator.snacks.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.*;

public class ItemLoader {


    private List<Snack> snacks = new ArrayList<>();
    private Map<String, Integer> quantity = new HashMap<>();

    public void loadInventory(String filePath) {
        File vend = new File(filePath);
        try (Scanner scanner = new Scanner(vend)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] lineArr = line.split("\\|");

                String code = lineArr[0];
                String name = lineArr[1];
                BigDecimal price = new BigDecimal(lineArr[2]);
                String type = lineArr[3];
                int stock = 5;

                if (type.equalsIgnoreCase("chip")) {
                    snacks.add(new Chip(code, name, price, type));
                    quantity.put(code, stock);
                } else if (type.equalsIgnoreCase("candy")) {
                    snacks.add(new Candy(code, name, price, type));
                    quantity.put(code, stock);
                } else if (type.equalsIgnoreCase("drink")) {
                    snacks.add(new Soda(code, name, price, type));
                    quantity.put(code, stock);
                } else if (type.equalsIgnoreCase("gum")) {
                    snacks.add(new Gum(code, name, price, type));
                    quantity.put(code, stock);
                }
            }
        } catch (FileNotFoundException e) {
            e.getMessage();
        }
    }

    public List<Snack> getSnacks() {
        return snacks;
    }

    public Map<String, Integer> getQuantity() {
        return quantity;
    }

    public Snack getNameFromCode(String code) {
        for (Snack snack : getSnacks()) {
            String thatSnack = snack.getSlotCode();
            if (thatSnack.equalsIgnoreCase(code)) {
                return snack;
            }
        }
        return null;
    }

    public String listCurrentInventory() {
        String current = "";
        for (Snack snack : snacks) {
            String slotCode = snack.getSlotCode();
            Integer stock = getQuantity().get(slotCode);
             current = (stock != 0) ? current + snack.toString() + "x" + stock + "\n"
                     : current + snack.toString() + "OUT OF STOCK! \n";
        }
        return current;
    }

    public void dispense(String code) {
        if (quantity.containsKey(code) && quantity.get(code) >= 1) {
            quantity.put(code, ((quantity.get(code) - 1)));
        } else {
            throw new IllegalArgumentException();
        }
    }

}
