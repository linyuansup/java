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
    private int port;
    private ServerSocket serverSocket;
    private Socket socket;
    private InputStream inputStream;
    private ByteArrayOutputStream byteArrayOutputStream;

    private String IP = null;
    private JTextArea textArea;
    private SocketClientRunner runner;

    public void setAsServer(int port, JTextArea textArea) {
        this.port = port;
        this.textArea = textArea;
    }

    private void addText(String s) {
        textArea.append(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")) + " " + s + "\n");
    }

    public void sendText(String s) {
        try {
            OutputStream os = socket.getOutputStream();
            os.write(s.getBytes("GBK"));
        } catch (Exception ex) {
            addText("发生错误" + ex);
        }
    }

    public void setAsClient(String IP, int port, JTextArea messageArea) {
        this.IP = IP;
        this.port = port;
        this.textArea = messageArea;
    }

    public void close() {
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

    public void startServer() {
        if (runner != null && runner.isAlive()) {
            addText("不允许多次连接");
            return;
        }
        runner = new SocketClientRunner();
        runner.start();
    }

    public class SocketClientRunner extends Thread {
        @Override
        public void run() {
            try {
                addText("开始连接......");
                if (IP == null) {
                    serverSocket = new ServerSocket(port);
                    socket = serverSocket.accept();
                } else {
                    socket = new Socket(IP, port);
                }
                addText("连接成功");
                inputStream = socket.getInputStream();
                byteArrayOutputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int len;
                while ((len = inputStream.read(buffer)) != -1) {
                    byteArrayOutputStream.write(buffer, 0, len);
                    addText(byteArrayOutputStream.toString("GBK"));
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
