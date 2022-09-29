package sy.b;

import javax.swing.*;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class SocketClient extends Thread {
    private int port;
    private ServerSocket serverSocket;
    private Socket socket;
    private InputStream inputStream;
    private ByteArrayOutputStream byteArrayOutputStream;

    private String IP = null;
    private JTextArea textArea;

    public void setAsServer(int port, JTextArea textArea) {
        this.port = port;
        this.textArea = textArea;
    }

    private void addText(String s) {
        textArea.append(s + "\n");
    }

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
            if (!(ex instanceof SocketException)) {
                textArea.setText("发生错误：" + ex);
            }
        } finally {
            close();
        }
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
            addText("连接断开");
        } catch (Exception ex) {
            addText("发生错误" + ex);
        }
    }

    public void startServer() {
        if (isAlive()) {
            addText("不允许多次连接");
            return;
        }
        start();
    }
}
