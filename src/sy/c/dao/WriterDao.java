package sy.c.dao;

import java.io.FileWriter;
import java.io.IOException;

/**
 * 数据库写入类
 */
public class WriterDao {
    private final FileWriter fileWriter; // 文件输出流

    /**
     * 构建一个数据库写入类
     *
     * @param path 数据库位置
     */
    public WriterDao(String path) {
        try {
            fileWriter = new FileWriter(path); // 指定文件位置
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 将数据写入文件，String[] 间以 tab 分割
     *
     * @param data 需要写入的数据
     * @throws IOException IO 错误
     */
    public void write(String[] data) throws IOException {
        StringBuilder line = new StringBuilder(); // 准备拼接字符串
        for (int i = 0; i < data.length - 1; i++) {
            line.append(data[i]).append("\t"); // 拼接并以 tab 分割
        }
        line.append(data[data.length - 1]); // 最后一项不需要 tab
        fileWriter.write(line + "\n");
    }

    /**
     * 关闭连接
     *
     * @throws IOException IO 错误
     */
    public void close() throws IOException {
        fileWriter.close();
    }
}
