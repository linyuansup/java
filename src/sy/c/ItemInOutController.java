package sy.c;

import java.io.*;

public class ItemInOutController {
    public static void main(String[] args) {

    }
}

class Item {
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

class DBConnect {
    private final BufferedWriter bw;
    private final BufferedReader br;

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public DBConnect(String path) throws IOException {
        File file = new File(path);
        file.createNewFile();
        br = new BufferedReader(new FileReader(path));
        bw = new BufferedWriter(new FileWriter(path));
    }

    public void write(String s) {

    }
}
