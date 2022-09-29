package sy.b;

import javax.swing.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketClient extends Thread {
    private int port;
    private ServerSocket serverSocket;
    private Socket socket;
    private InputStream inputStream;
    private ByteArrayOutputStream byteArrayOutputStream;
    private OutputStream os;

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
            textArea.setText("发生错误：" + ex);
        } finally {
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
    }

    public void sendText(String s) {
        try {
            os = socket.getOutputStream();
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
}
