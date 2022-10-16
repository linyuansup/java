package sy.c.dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

/**
 * 数据库读取类
 */
public class ReaderDao {
    private final BufferedReader bufferedReader; // 文件读取流

    /**
     * 建立一个新的数据库读取连接
     *
     * @param path 数据库路径
     */
    public ReaderDao(String path) {
        try {
            bufferedReader = new BufferedReader(new FileReader(path)); // 指定文件位置
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 从数据库中读取全部数据
     *
     * @return 数据库中每一条数据为一个 String[] 对象，String[] 对象由每一个部分切片组成
     * @throws IOException IO 错误
     */
    public Vector<String[]> read() throws IOException {
        Vector<String[]> result = new Vector<>();
        String line = bufferedReader.readLine();
        while (line != null) {
            result.add(line.split("\t")); // 用 tab 分割
            line = bufferedReader.readLine(); // 读取下一行
        }
        return result;
    }

    /**
     * 关闭连接
     *
     * @throws IOException IO 错误
     */
    public void close() throws IOException {
        bufferedReader.close();
    }
}
