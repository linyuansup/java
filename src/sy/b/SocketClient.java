package sy.b;

import javax.swing.*;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class SocketClient {
    private int port; // 端口号
    private ServerSocket serverSocket; // Socket 服务器初始化实例
    private Socket socket; // Socket
    private InputStream inputStream; // 输入流
    private ByteArrayOutputStream byteArrayOutputStream; // 字节数组输出流

    private String IP = null; // IP 地址
    private JTextArea textArea; // 文本区域
    private SocketClientRunner runner; // Socket 客户端运行器

    /**
     * 设置为服务端
     *
     * @param port     端口号
     * @param textArea 显示的文本区域
     */
    public void setAsServer(int port, JTextArea textArea) {
        this.port = port;
        this.textArea = textArea;
    }

    /**
     * 向文本区域添加文本
     *
     * @param s 需要添加的文本
     */
    private void addText(String s) {
        textArea.append(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")) + " " + s + "\n");
    }

    /**
     * 发送文本
     *
     * @param s 文本内容
     */
    public void sendText(String s) {
        try {
            OutputStream os = socket.getOutputStream();
            os.write(s.getBytes("GBK"));
        } catch (Exception ex) {
            addText("发生错误" + ex);
        }
    }

    /**
     * 设置为客户端
     *
     * @param IP          IP 地址
     * @param port        端口号
     * @param messageArea 显示的文本区域
     */
    public void setAsClient(String IP, int port, JTextArea messageArea) {
        this.IP = IP;
        this.port = port;
        this.textArea = messageArea;
    }

    /**
     * 关闭资源
     */
    private void close() {
        try {
            if (serverSocket != null) {
                serverSocket.close();
            }
            if (socket != null) {
                socket.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
            if (byteArrayOutputStream != null) {
                byteArrayOutputStream.close();
            }
        } catch (Exception ex) {
            addText("发生错误" + ex);
        }
    }

    /**
     * 启动服务器
     */
    public void startServer() {
        if (runner != null && runner.isAlive()) {
            addText("不允许多次连接");
            return;
        }
        runner = new SocketClientRunner();
        runner.start();
    }

    // Socket 客户端的运行实例
    public class SocketClientRunner extends Thread {
        @Override
        public void run() {
            try {
                addText("开始连接......");
                // 初始化 Socket 服务器
                // 如果 IP 地址不存在，则为服务端，否则为客户端
                if (IP == null) {
                    serverSocket = new ServerSocket(port);
                    socket = serverSocket.accept();
                } else {
                    socket = new Socket(IP, port);
                }
                addText("连接成功");
                // 初始化输入流
                inputStream = socket.getInputStream();
                // 初始化字节数组输出流
                byteArrayOutputStream = new ByteArrayOutputStream();
                // 初始化缓冲区
                byte[] buffer = new byte[1024];
                int len;
                // 循环读取数据，len 不为 -1 则读取成功
                while ((len = inputStream.read(buffer)) != -1) {
                    // 将读取到的数据写入字节数组输出流
                    byteArrayOutputStream.write(buffer, 0, len);
                    // 将字节数组输出流转换为字符串并添加进文本
                    addText(byteArrayOutputStream.toString("GBK"));
                    // 清空字节数组输出流
                    byteArrayOutputStream.reset();
                }
            } catch (Exception ex) {
                if (ex instanceof BindException) {
                    addText("端口被占用");
                } else if (ex instanceof SocketException) {
                    if (ex.getMessage().equals("Connection refused: connect")) {
                        addText("服务器未启动");
                    } else {
                        addText("连接断开");
                    }
                } else {
                    addText("发生错误：" + ex);
                }
            } finally {
                close();
            }
        }
    }
}
