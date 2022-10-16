package sy.c.transaction;


import sy.c.Factory;
import sy.c.Item;

/**
 * R 操作类
 */
public class ReceiveTransaction extends Transaction {

    private final int quantity; // 数量

    /**
     * 构建一个收到货物的事务
     *
     * @param itemNumber 物品 ID
     * @param quantity   物品数量
     */
    public ReceiveTransaction(String itemNumber, int quantity) {
        this.quantity = quantity;
        this.itemNumber = itemNumber;
    }

    /**
     * 处理该事件
     *
     * @param factory 操作的工厂
     */
    @Override
    public void divide(Factory factory) {
        factory.addItem(new Item(itemNumber, quantity, null, null));
    }
}