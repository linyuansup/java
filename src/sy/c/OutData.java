package sy.c;


/**
 * 输出数据类
 */
class OutData {

    private final String customer; // 客户
    private final String item; // 物品
    private int quantity; // 数量

    /**
     * 构建一个新的输出记录
     *
     * @param customer 客户
     * @param item     物品 ID
     * @param quantity 数量
     */
    public OutData(String customer, String item, int quantity) {
        this.customer = customer;
        this.item = item;
        this.quantity = quantity;
    }

    /**
     * 获取客户信息
     *
     * @return 客户信息
     */
    public String getCustomer() {
        return customer;
    }

    /**
     * 获取物品 ID
     *
     * @return 物品 ID
     */
    public String getItem() {
        return item;
    }

    /**
     * 添加该输出记录的数量
     *
     * @param num 增加的数量
     */
    public void add(int num) {
        quantity += num;
    }

    public String[] toStrings() {
        return new String[]{customer, item, String.valueOf(quantity)};
    }
}
