package sy.c;


import java.util.Vector;

/**
 * 工厂类
 */
public class Factory {

    /**
     * 获取货物库
     *
     * @return 所有货物
     */
    public Vector<Item> getItems() {
        return items;
    }

    private final Vector<Item> items = new Vector<>(); // 产品库
    private final Vector<OutData> sent = new Vector<>(); // 已经发出的列表
    private final Vector<OutData> error = new Vector<>(); // 出问题的列表

    /**
     * 添加产品数据
     *
     * @param needAdd 需要添加的产品
     */
    public void addItem(Item needAdd) {
        for (Item item : items) {
            if (item.getItemNumber().equals(needAdd.getItemNumber())) {
                item.add(needAdd.getQuantity()); // 如果找到了这个货物，就直接加一定数量
                return;
            }
        }
        items.add(needAdd); // 找不到该货物，直接加入
    }

    /**
     * 添加一个发出的记录
     *
     * @param customer 客户
     * @param item     物品 ID
     * @param quantity 数量
     */
    public void addSend(String customer, String item, int quantity) {
        for (OutData outData : sent) {
            if (outData.getCustomer().equals(customer) && outData.getItem().equals(item)) {
                outData.add(quantity); // 如果找到了这个记录，就直接增加数量
                return;
            }
        }
        // 如果找不到记录，就加一个新的
        sent.add(new OutData(customer, item, quantity));
    }

    /**
     * 添加一个错误的记录
     *
     * @param customer 客户
     * @param item     物品 ID
     * @param quantity 数量
     */
    public void addError(String customer, String item, int quantity) {
        error.add(new OutData(customer, item, quantity)); // 添加一定数量的货物
    }

    /**
     * 从仓库中移除一定数量的货物
     *
     * @param itemID 货物 ID
     * @param num    数量
     * @return 是否移除成功
     */
    public boolean decreaseItem(String itemID, int num) {
        for (Item item : items) {
            if (item.getItemNumber().equals(itemID)) {
                return item.decrease(num); // 找到并移除货物
            }
        }
        return false;
    }

    /**
     * 从仓库中移除货物
     *
     * @param itemNumber 货物编号
     * @return 货物被删除时剩余量
     */
    public int deleteItem(String itemNumber) {
        for (Item item : items) {
            if (item.getItemNumber().equals(itemNumber)) {
                int num = item.getQuantity();
                // 循环寻找对应的货物
                if (num == 0) {
                    // 如果没有余货，则删除货物
                    items.remove(item);
                }
                // 返回货物剩余量
                return num;
            }
        }
        return 0;
    }

    /**
     * 获取错误单
     *
     * @return 错误单
     */
    public Vector<OutData> getError() {
        return error;
    }

    /**
     * 获取发货单
     *
     * @return 发货单
     */
    public Vector<OutData> getSent() {
        return sent;
    }
}