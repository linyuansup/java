package sy.c;

import sy.c.dao.ReaderDao;
import sy.c.dao.WriterDao;
import sy.c.transaction.AddTransaction;
import sy.c.transaction.DeleteTransaction;
import sy.c.transaction.OutTransaction;
import sy.c.transaction.ReceiveTransaction;

import java.io.IOException;
import java.util.Comparator;
import java.util.Vector;

public class Inventory {

    public static void main(String[] args) {
        ReaderDao inventory = new ReaderDao("src/sy/c/Inventory.txt");
        ReaderDao transaction = new ReaderDao("src/sy/c/Transaction.txt");
        Vector<String[]> invData; // 货物清单
        Vector<String[]> tranData; // 事务清单
        try {
            invData = inventory.read(); // 货物数据
            tranData = transaction.read(); // 事务数据
            inventory.close();
            transaction.close();
        } catch (IOException e) {
            System.out.println("数据访问错误");
            return;
        }
        Factory factory = new Factory(); // 工厂
        // 遍历货物数据，添加进工厂
        for (String[] inv : invData) {
            factory.addItem(new Item(inv[0], Integer.parseInt(inv[1]), inv[2], inv[3]));
        }
        Vector<OutTransaction> outTransactions = new Vector<>(); // 发货订单
        Vector<ReceiveTransaction> receiveTransactions = new Vector<>(); // 到货订单
        Vector<AddTransaction> addTransactions = new Vector<>(); // 添加新货物订单
        Vector<DeleteTransaction> deleteTransactions = new Vector<>(); // 删除货物订单
        for (String[] item : tranData) {
            switch (item[0]) {
                case "O" -> // 是一条发货订单
                        outTransactions.add(new OutTransaction(item[1], Integer.parseInt(item[2]), item[3]));
                case "R" -> // 是一条到货订单
                        receiveTransactions.add(new ReceiveTransaction(item[1], Integer.parseInt(item[2])));
                case "A" -> // 是一条货物增加订单
                        addTransactions.add(new AddTransaction(item[1], item[2], item[3]));
                case "D" -> // 是一条删除订单
                        deleteTransactions.add(new DeleteTransaction(item[1]));
                default -> {
                    System.out.println("数据库错误");
                    return;
                }
            }
        }
        outTransactions.sort(Comparator.comparingInt(OutTransaction::getQuantity)); // 发货订单根据数量排序
        // 先执行货物补充订单
        for (AddTransaction addTransaction : addTransactions) {
            addTransaction.divide(factory);
        }
        // 再执行到货单
        for (ReceiveTransaction receiveTransaction : receiveTransactions) {
            receiveTransaction.divide(factory);
        }
        // 再执行发货单
        for (OutTransaction outTransaction : outTransactions) {
            outTransaction.divide(factory);
        }
        // 再执行删货单
        for (DeleteTransaction deleteTransaction : deleteTransactions) {
            deleteTransaction.divide(factory);
        }
        Vector<OutData> sent = factory.getSent(); // 获取发货单
        Vector<OutData> error = factory.getError(); // 获取错误单
        Vector<Item> items = factory.getItems(); // 获取所有货物
        // 连接数据库
        WriterDao shipping = new WriterDao("src/sy/c/Shipping.txt");
        WriterDao errors = new WriterDao("src/sy/c/Errors.txt");
        WriterDao newInventory = new WriterDao("src/sy/c/NewInventory.txt");
        try {
            // 遍历发送单，输出内容
            for (OutData sentData : sent) {
                shipping.write(sentData.toStrings());
            }
            shipping.close();
            // 遍历错误单，输出内容
            for (OutData errorData : error) {
                errors.write(errorData.toStrings());
            }
            errors.close();
            // 遍历货物单，输出内容
            for (Item item : items) {
                newInventory.write(item.toStrings());
            }
            newInventory.close();
        } catch (IOException e) {
            System.out.println("数据写入错误");
            return;
        }
        System.out.println("处理完成");
    }
}