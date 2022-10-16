package sy.c.transaction;

import sy.c.Factory;

/**
 * D 操作类
 */
public class DeleteTransaction extends Transaction {
    public DeleteTransaction(String itemNumber) {
        this.itemNumber = itemNumber;
    }

    /**
     * 处理该事件
     *
     * @param factory 操作的工厂
     */
    @Override
    public void divide(Factory factory) {
        int num = factory.deleteItem(itemNumber);
        if (num != 0) {
            // 如果删除失败，则写入错误记录
            factory.addError("0", itemNumber, num);
        }
    }
}
