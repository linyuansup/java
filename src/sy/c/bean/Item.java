package sy.c.bean;

public class Item {
    private final int itemNumber;
    private final int quantity;
    private final int supplier;
    private final String description;

    public Item(int itemNumber, int quantity, int supplier, String description) {
        this.itemNumber = itemNumber;
        this.quantity = quantity;
        this.supplier = supplier;
        this.description = description;
    }

    public int getItemNumber() {
        return itemNumber;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getSupplier() {
        return supplier;
    }

    public String getDescription() {
        return description;
    }
}
