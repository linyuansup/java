package sy.b.server;

import sy.b.ui.BottomBar;
import sy.b.ui.MiddleBar;
import sy.b.ui.ServerTopBar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        JFrame mainUI = new JFrame();
        ServerTopBar serverTopBar = new ServerTopBar();
        MiddleBar middleBar = new MiddleBar();
        BottomBar bottomBar = new BottomBar();
        serverTopBar.setClick(e -> new Thread(() -> {
            try (ServerSocket server = new ServerSocket(serverTopBar.getPort())) {
                middleBar.setText("开始建立服务器连接......");
                Socket socket = server.accept();
                middleBar.setText("连接成功");
                while (true) {
                    InputStream inputStream = socket.getInputStream();
                    inputStream.read()
                    middleBar.setText(new String(socket.getInputStream().readAllBytes(), "GBK"));
                }
            } catch (Exception ex) {
                middleBar.setText("发生错误：" + ex);
            }
        }).start());
        mainUI.setLayout(new BorderLayout());
        mainUI.setTitle("服务端");
        mainUI.setSize(500, 500);
        mainUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainUI.add(serverTopBar, BorderLayout.NORTH);
        mainUI.add(middleBar, BorderLayout.CENTER);
        mainUI.add(bottomBar, BorderLayout.SOUTH);
        mainUI.setVisible(true);
    }
}
