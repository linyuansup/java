package sy.c.transaction;

import sy.c.Factory;

/**
 * O 操作类
 */
public class OutTransaction extends Transaction {
    public int getQuantity() {
        return quantity;
    }

    private final int quantity; // 数量
    private final String customer; // 客户

    public OutTransaction(String itemNumber, int quantity, String customer) {
        this.customer = customer;
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
        if (factory.decreaseItem(itemNumber, quantity)) {
            // 如果成功移除，则添加一条记录到发货单
            factory.addSend(customer, itemNumber, quantity);
        } else {
            // 如果移除失败，则添加一条记录到错误单
            factory.addError(customer, itemNumber, quantity);
        }
    }
}