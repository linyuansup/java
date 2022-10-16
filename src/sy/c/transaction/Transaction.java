package sy.c.transaction;

import sy.c.Factory;

/**
 * 操作类
 */
abstract class Transaction {

    protected String itemNumber; // 货物编号

    /**
     * 处理该事件
     *
     * @param factory 操作的工厂
     */
    abstract public void divide(Factory factory);
}