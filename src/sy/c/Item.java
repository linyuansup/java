package sy.c;

/**
 * 产品类
 */
public class Item {
    private final String itemNumber; // 货物编号

    private int quantity; // 数量
    private final String supplier; // 供应商

    private final String description; // 描述信息

    /**
     * 构建一个产品对象
     *
     * @param itemNumber  货物编号
     * @param quantity    数量
     * @param supplier    供应商
     * @param description 描述信息
     */
    public Item(String itemNumber, int quantity, String supplier, String description) {
        this.itemNumber = itemNumber;
        this.quantity = quantity;
        this.supplier = supplier;
        this.description = description;
    }

    /**
     * 获取货品 ID
     *
     * @return 货物 ID
     */
    public String getItemNumber() {
        return itemNumber;
    }

    /**
     * 添加产品数量
     *
     * @param num 添加的数量
     */
    public void add(int num) {
        quantity += num;
    }

    /**
     * 获取产品数量
     *
     * @return 产品数量
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * 移除一定数量的货物
     *
     * @param num 货物数量
     * @return 是否移除成功
     */
    public boolean decrease(int num) {
        if (quantity >= num) {
            quantity -= num; // 如果数量够，则移除货物
            return true;
        } else {
            return false; // 数量不够，则不移除
        }
    }

    /**
     * 转换为 String 数组
     *
     * @return String 数组
     */
    public String[] toStrings() {
        return new String[]{itemNumber, String.valueOf(quantity), supplier, description};
    }
}