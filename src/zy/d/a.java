package zy.d;

public class a {
    public static void main(String[] args) {
        Pool primePool = new Pool(); // 素数池
        Pool perfectPool = new Pool(); // 完全数池
        Pool resultPool = new Pool(); // 结果池
        PerfectGenerator perfectGenerator = new PerfectGenerator(perfectPool); // 完全数生成器
        PrimeGenerator primeGenerator = new PrimeGenerator(primePool); // 素数生成器
        Adder perfectAdder = new Adder(perfectPool, resultPool); // 完全数加法器
        Adder primeAdder = new Adder(primePool, resultPool); // 素数加法器
        // 开线程
        perfectGenerator.start();
        primeGenerator.start();
        perfectAdder.start();
        primeAdder.start();
        System.out.println((long) resultPool.get() * resultPool.get()); // int 会炸，结果池两个数乘在一起就好了
    }
}

// 素数生成
class PrimeGenerator extends Thread {
    private final Pool pool;

    public PrimeGenerator(Pool pool) {
        this.pool = pool;
    }

    @Override
    public void run() {
        // 素数判断的范围其实从 2 到 sqrt(x) 就行了，压根不用算完
        // 但这样做需要先加 2 和 3
        pool.add(2);
        pool.add(3);
        for (int result = 5; result <= 10000; result++) {
            boolean isPrime = true; // 默认是素数
            for (int i = 2; i <= Math.sqrt(result); i++) {
                if (result % i == 0) {
                    isPrime = false; // 如果能整除，则不是素数
                    break;
                }
            }
            if (isPrime) {
                pool.add(result); // 如果是素数，就进池子
            }
        }
        pool.add(0); // 标记结束
    }
}

// 完全数生成
class PerfectGenerator extends Thread {
    private final Pool pool;

    public PerfectGenerator(Pool pool) {
        this.pool = pool;
    }

    @Override
    public void run() {
        for (int result = 2; result <= 10000; result++) {
            int now = result;
            for (int i = 1; i < result; i++) {
                if (result % i == 0) {
                    now -= i; // 如果能除尽，就把他减了
                }
            }
            if (now == 0) {
                pool.add(result); // 如果最终为 0，则为完全数，加池子里
            }
        }
        pool.add(0); // 标记结束
    }
}

// 加起来的类
class Adder extends Thread {
    private final Pool pool;
    private final Pool resultPool;

    public Adder(Pool pool, Pool resultPool) {
        this.pool = pool;
        this.resultPool = resultPool;
    }

    @Override
    public void run() {
        int result = 0;
        int num;
        do {
            num = pool.get();
            result += num;
        } while (num != 0);
        resultPool.add(result);
    }
}

// 缓存池
class Pool {
    private final int[] result = new int[500]; // 没用队列啥的，故意限制了长度，制造堵塞
    private int index = 0;

    /**
     * 向缓存池添加数据
     *
     * @param num 添加的数据
     */
    public synchronized void add(int num) {
        while (index == 499) {
            // 如果池子满了，就停下来
            try {
                this.wait(); // wait 会释放同步锁，不用怕睡死
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        this.notify(); // 如果没满，先把进程叫起来
        result[index] = num; // 存数据
        index++; // 动指针
    }

    /**
     * 从缓存池获取数据并退池
     *
     * @return 缓存池中的数据
     */
    public synchronized int get() {
        while (index == 0) {
            // 如果池子空了，就停下来
            try {
                this.wait(); // wait 释放锁，所以不用怕睡死
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        this.notify(); // 如果池子没空，就把进程叫起来
        index--;
        return result[index]; // 出数据，动指针
    }
}
