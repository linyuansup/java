package sy.c.transaction;

import sy.c.Factory;
import sy.c.Item;

/**
 * A 操作类
 */
public class AddTransaction extends Transaction {
    private final String supplier; // 供应商
    private final String description; // 货物描述

    /**
     * 构建一个新的增加货物事务
     *
     * @param itemNumber 货物 ID
     * @param supplier 供应商
     * @param description 货物描述
     */
    public AddTransaction(String itemNumber, String supplier, String description) {
        this.supplier = supplier;
        this.description = description;
        this.itemNumber = itemNumber;
    }

    /**
     * 处理该事件
     *
     * @param factory 操作的工厂
     */
    @Override
    public void divide(Factory factory) {
        factory.addItem(new Item(itemNumber, 0, supplier, description));
    }
}